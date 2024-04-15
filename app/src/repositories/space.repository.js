import axios from "axios";

const spaceRepo = {
  spaceInit: async (accessToken) => {
    return axios.get("/space/init", {
      headers: {
        authorization: accessToken,
      },
    });
  },
};

export default spaceRepo;
