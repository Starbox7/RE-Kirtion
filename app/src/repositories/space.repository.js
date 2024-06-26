import axios from "axios";

const spaceRepo = {
  spaceInit: async (accessToken) => {
    const response = await axios.get("/space/init", {
      headers: {
        authorization: accessToken,
      },
    });

    return response;
  },

  createNewPage: async (data) => {
    const response = await axios.post(
      "/space/page/create",
      {
        workspace_uuid: data.uuid,
      },
      {
        headers: {
          authorization: data.accessToken,
        },
      }
    );

    return response;
  },

  selectPage: async (data) => {
    return await axios.post(
      "/space/page/select",
      {
        page_uuid: data.pageUuid,
        workspace_uuid: data.workspaceUuid,
      },
      {
        headers: {
          authorization: data.accessToken,
        },
      }
    );
  },

  updatePage: async (data) => {
    return await axios.post(
      "/space/page/update",
      {
        page_uuid: data.pageUuid,
        title: data.title,
        text: data.text,
      },
      {
        headers: {
          authorization: data.accessToken,
        },
      }
    );
  },

  updateBlock: async (data) => {
    return await axios.post(
      "/space/page/block/update",
      {
        block_uuid: data.uuid,
        type: data.type,
        data: data.data,
      },
      {
        headers: {
          authorization: data.accessToken,
        },
      }
    );
  },
  createBlock: async (data) => {
    return await axios.post(
      "/space/page/block/create",
      {
        block_uuid: data.uuid,
        type: data.type,
        count: data.newCount,
      },
      {
        headers: {
          authorization: data.accessToken,
        },
      }
    );
  },
  createBlockImage: async (data) => {
    return await axios.post(
      "/space/image",

      data.formData,
      {
        headers: {
          "Content-Type": "multipart/form-data",
          authorization: data.accessToken,
          uuid: data.newUuid,
        },
      }
    );
  },
  deleteBlock: async (data) => {
    return await axios.post(
      "/space/page/block/delete",
      {
        block_uuid: data.uuid,
      },
      {
        headers: {
          authorization: data.accessToken,
        },
      }
    );
  },
};

export default spaceRepo;
