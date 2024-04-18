import { useEffect, useRef, useState } from "react";
import { ContentAutoArea } from "../home/home.style";
import useAuthorize from "./hooks/useAuthorize";
import { useMutation } from "@tanstack/react-query";
import spaceRepo from "../../repositories/space.repository";
import { useDispatch, useSelector } from "react-redux";
import {
  setBlockListInPersonalPageList,
  setCurrentPage,
  setCurrentPageBlockList,
  setCurrentWorkspace,
  setPageListInTeamspaceList,
} from "../../features/space.slice";
import { setUpdateBlockState } from "../../features/state.slice";
import ListView from "./listview";

export default function Block({ blockData, currentPage, title }) {
  const [text, setText] = useState("");
  const { getAccessToken } = useAuthorize();
  const mutation = useMutation({ mutationFn: spaceRepo.updateBlock });
  const dispatch = useDispatch();
  const currentPageBlockList = useSelector(
    (state) => state.space.currentPageBlockList
  );
  const updateBlockState = useSelector((state) => state.state.updateBlockState);

  const textareaRef = useRef(null);
  const [modalPosition, setModalPosition] = useState(null);
  const [currentLineLength, setCurrentLineLength] = useState(0);

  const onTextChangeHandler = (e) => {
    const value = e.target.value;
    setText(value);
    const position = e.target.selectionStart;

    // 현재 줄의 길이를 계산하는 로직을 개선
    updateCurrentLineLength(value, position);

    if (value[position - 1] === "/") {
      calculateAndDisplayModal(position);
    } else {
      setModalPosition(null); // `/`가 아닐 때 모달 위치 제거
    }

    const uuid = blockData.uuid;
    const type = blockData.type;
    const data = value;
    const accessToken = getAccessToken();
    mutation.mutateAsync({ uuid, type, data, accessToken }).then((result) => {
      dispatch(setCurrentPage(result.data.current_page));
      dispatch(setCurrentPageBlockList(result.data.current_page_block_list));
      dispatch(setCurrentWorkspace(result.data.current_workspace));
      dispatch(
        setBlockListInPersonalPageList(
          result.data.block_list_in_personal_page_list
        )
      );
      dispatch(
        setPageListInTeamspaceList(result.data.page_list_in_teamspace_list)
      );
    });
  };

  const listViewRef = useRef(null);

  const updateCurrentLineLength = (text, cursorPosition) => {
    const beforeText = text.substring(0, cursorPosition);
    const afterText = text.substring(cursorPosition);

    const startOfLine = beforeText.lastIndexOf("\n") + 1;
    const endOfLine = cursorPosition + afterText.indexOf("\n");

    const currentLineText = text.substring(
      startOfLine,
      endOfLine === -1 ? text.length : endOfLine
    );
    setCurrentLineLength(currentLineText.length);
  };

  const calculateAndDisplayModal = (cursorPosition) => {
    const textarea = textareaRef.current;
    const listView = listViewRef.current;
    const textareaRect = textarea.getBoundingClientRect();

    // 기본 위치 계산
    let modalTop = textareaRect.top + textareaRect.height + window.scrollY;
    const modalLeft = textareaRect.left + currentLineLength * 13; // 각 글자 폭을 대략적으로 13px로 계산

    // ListView 높이 확인
    const listViewHeight = listView?.offsetHeight || 300; // 기본 높이를 300px로 가정

    // 화면 높이와 비교
    if (modalTop + listViewHeight > window.innerHeight + window.scrollY) {
      modalTop = textareaRect.top - listViewHeight + window.scrollY; // 위로 이동
    }

    setModalPosition({ top: modalTop, left: modalLeft });
  };

  useEffect(() => {
    if (updateBlockState) {
      setText(blockData.data);
      dispatch(setUpdateBlockState(false));
    }
  }, [currentPageBlockList]);

  return (
    <>
      <ContentAutoArea
        ref={textareaRef}
        key={blockData.uuid}
        value={text}
        onChange={onTextChangeHandler}
        placeholder="Write something, or press 'space' for AI, '/' for commands..."
      />
      {modalPosition && <ListView ref={listViewRef} position={modalPosition} />}
    </>
  );
}
