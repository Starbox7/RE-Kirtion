import { useEffect, useRef, useState } from "react";
import { ContentAutoArea, ContentTitleArea } from "../home/home.style";
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
import {
  setUpdateBlockState,
  setUpdateState,
} from "../../features/state.slice";
import ListView from "./listview";
import { Box, CircularProgress } from "@mui/material";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSquareMinus } from "@fortawesome/free-regular-svg-icons";

export default function Block({ blockData, currentPage, title, length }) {
  const [text, setText] = useState("");
  const { getAccessToken } = useAuthorize();
  const mutation = useMutation({ mutationFn: spaceRepo.updateBlock });
  const imageMutation = useMutation({ mutationFn: spaceRepo.createBlockImage });

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

  const selectOptionHandler = () => {
    const stringData = text.slice(0, -1);
    setText(stringData);
    setModalPosition(false);
  };

  const handleFileChange = (event) => {
    const accessToken = getAccessToken();
    const file = event.target.files[0];
    const formData = new FormData();
    formData.append("file", file);
    const newUuid = blockData.uuid;
    imageMutation
      .mutateAsync({ formData, accessToken, newUuid })
      .then((result) => {
        dispatch(setUpdateState(true));
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

  const deleteMutation = useMutation({ mutationFn: spaceRepo.deleteBlock });
  const handleDeleteBlock = (event) => {
    const accessToken = getAccessToken();
    const deleteUuid = blockData.uuid;
    deleteMutation
      .mutateAsync({ uuid: deleteUuid, accessToken })
      .then((result) => {
        dispatch(setUpdateState(true));
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

  const [loading, setLoading] = useState(true);
  const [isMouseOn, setIsMouseOn] = useState(false);

  return (
    <>
      {blockData.type == "title" && (
        <Box
          onMouseEnter={() => setIsMouseOn(true)}
          onMouseLeave={() => setIsMouseOn(false)}
          sx={{
            display: "flex",
            alignItems: "center",
            justifyContent: "flex-start",
            // border: "1px solid black",
            marginBottom: "15px",
            padding: "0 100px",
            paddingLeft: isMouseOn ? "60px" : "70px",
          }}
        >
          <FontAwesomeIcon
            icon={faSquareMinus}
            color="magenta"
            style={{
              marginRight: "10px",
              opacity: isMouseOn ? 1 : 0,
              visibility: isMouseOn ? "visible" : "hidden",
              transition: "opacity 0.3s ease, visibility 0.3s ease",
            }}
            onClick={handleDeleteBlock}
          />
          <ContentTitleArea
            ref={textareaRef}
            key={blockData.uuid}
            value={text}
            onChange={onTextChangeHandler}
            placeholder="Title.."
          />
        </Box>
      )}
      {blockData.type == "text" && (
        <Box
          onMouseEnter={() => setIsMouseOn(true)}
          onMouseLeave={() => setIsMouseOn(false)}
          sx={{
            display: "flex",
            alignItems: "center",
            justifyContent: "flex-start",
            // border: "1px solid black",
            marginBottom: "15px",
            padding: "0 100px",
            paddingLeft: isMouseOn ? "60px" : "70px",
          }}
        >
          <FontAwesomeIcon
            icon={faSquareMinus}
            color="magenta"
            style={{
              marginRight: "10px",
              opacity: isMouseOn ? 1 : 0,
              visibility: isMouseOn ? "visible" : "hidden",
              transition: "opacity 0.3s ease, visibility 0.3s ease",
            }}
            onClick={handleDeleteBlock}
          />
          <ContentAutoArea
            ref={textareaRef}
            key={blockData.uuid}
            value={text}
            onChange={onTextChangeHandler}
            placeholder="Write something, or press 'space' for AI, '/' for commands..."
          />
        </Box>
      )}
      {blockData.type == "image" && blockData.data == "" && (
        <Box
          onMouseEnter={() => setIsMouseOn(true)}
          onMouseLeave={() => setIsMouseOn(false)}
          sx={{
            display: "flex",
            alignItems: "center",
            justifyContent: "flex-start",
            // border: "1px solid black",
            marginBottom: "15px",
            // padding: "0 100px",
            paddingLeft: isMouseOn ? "60px" : "70px",
            paddingRight: isMouseOn ? "60px" : "70px",
            // border: "1px solid black",
            // borderRadius: "10px",
          }}
        >
          <FontAwesomeIcon
            icon={faSquareMinus}
            color="magenta"
            style={{
              marginRight: "10px",
              opacity: isMouseOn ? 1 : 0,
              visibility: isMouseOn ? "visible" : "hidden",
              transition: "opacity 0.3s ease, visibility 0.3s ease",
            }}
            onClick={handleDeleteBlock}
          />
          <Box
            sx={{
              display: "flex",
              width: "100%",
              alignItems: "center",
              // justifyContent: "flex-start",
              // border: "1px solid black",
              // margin: "15px",
              padding: "20px",
              background: "#F2F1EE",
            }}
          >
            <img
              style={{ width: "20px", height: "20px", marginRight: "7px" }}
              alt=""
              src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1592468667/noticon/mexcsjupsjgrmsoikhpj.png"
            />
            <label
              htmlFor="file-upload"
              className="custom-file-upload"
              style={{ color: "#808080" }}
            >
              Upload Image here.
            </label>
            <input
              id="file-upload"
              type="file"
              accept="image/jpeg, image/png, image/gif"
              style={{ display: "none" }}
              onChange={handleFileChange}
            />
          </Box>
        </Box>
      )}
      {blockData.type == "image" && blockData.data != "" && (
        <Box
          onMouseEnter={() => setIsMouseOn(true)}
          onMouseLeave={() => setIsMouseOn(false)}
          sx={{
            display: "flex",
            // justifyContent: "flex-start",
            alignItems: "center",
            // border: "1px solid black",
            marginBottom: "15px",
            paddingLeft: isMouseOn ? "60px" : "70px",
            paddingRight: isMouseOn ? "60px" : "70px",
          }}
        >
          <FontAwesomeIcon
            icon={faSquareMinus}
            color="magenta"
            style={{
              marginRight: "10px",
              opacity: isMouseOn ? 1 : 0,
              visibility: isMouseOn ? "visible" : "hidden",
              transition: "opacity 0.3s ease, visibility 0.3s ease",
            }}
            onClick={handleDeleteBlock}
          />
          <Box
            sx={{
              display: "flex",
              alignItems: "center",
              justifyContent: "flex-start",
              // border: "1px solid black",
              // margin: "15px",
              // padding: "20px",
              background: "#F2F1EE",
              visibility: loading ? "visible" : "hidden",
            }}
          >
            <CircularProgress />
          </Box>

          <img
            alt=""
            src={"https://localhost:8443/space" + blockData.data}
            style={{
              display: "flex",
              marginLeft: "100px",
              marginRight: "100px",
              width: "30%",
              visibility: loading ? "hidden" : "visible",
            }}
            onLoad={() => {
              setLoading(false);
            }}
          />
        </Box>
      )}
      {modalPosition && (
        <ListView
          ref={listViewRef}
          position={modalPosition}
          select={selectOptionHandler}
          uuid={blockData.uuid}
          count={length}
        />
      )}
    </>
  );
}
