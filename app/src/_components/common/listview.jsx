import { Box } from "@mui/material";

export default function ListView({ position }) {
  return (
    <Box
      sx={{
        position: "absolute",
        top: position.top,
        left: position.left,
        zIndex: 1000,

        display: "flex",
        flexDirection: "column",
        width: "200px",
        height: "300px",
        border: "1px solid #ddd",
        borderRadius: "15px",
        padding: "15px",
        background: "white",
      }}
    >
      <Box sx={{ fontSize: "12px", color: "gray" }}>Basic Blocks</Box>
      <Box
        sx={{
          display: "flex",
          justifyContent: "flex-start",
          alignItems: "center",
          flex: "1",
          width: "190px",
          margin: "10px 10px",
          borderRadius: "10px",
          "&:hover": {
            backgroundColor: "#E8E8E5",
          },
        }}
      >
        <img
          src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1602752070/noticon/wfyb5mf27do4w7erzvau.png"
          alt=""
          style={{
            width: "35px",
            height: "35px",
            paddingLeft: "10px",
          }}
        />
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            alignItems: "flex-start",
            marginLeft: "10px",
          }}
        >
          <Box sx={{ fontWeight: "bold" }}>Title</Box>
          <Box sx={{ fontSize: "11px" }}>Big section heading.</Box>
        </Box>
      </Box>
      <Box
        sx={{
          display: "flex",
          justifyContent: "flex-start",
          alignItems: "center",
          flex: "1",
          width: "190px",
          margin: "10px 10px",
          borderRadius: "10px",
          "&:hover": {
            backgroundColor: "#E8E8E5",
          },
        }}
      >
        <img
          src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1602338370/noticon/kmmq4tea9emvc0qcwl0u.png"
          alt=""
          style={{
            width: "35px",
            height: "35px",
            paddingLeft: "10px",
          }}
        />
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            alignItems: "flex-start",
            marginLeft: "10px",
          }}
        >
          <Box sx={{ fontWeight: "bold" }}>Text</Box>
          <Box sx={{ fontSize: "11px" }}>Input plain text.</Box>
        </Box>
      </Box>
      <Box
        sx={{
          display: "flex",
          justifyContent: "flex-start",
          alignItems: "center",
          flex: "1",
          width: "190px",
          margin: "10px 10px",
          borderRadius: "10px",
          "&:hover": {
            backgroundColor: "#E8E8E5",
          },
        }}
      >
        <img
          src="https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1644775356/noticon/xydfr6p7egplufr2jpqn.png"
          alt=""
          style={{
            width: "35px",
            height: "35px",
            paddingLeft: "10px",
          }}
        />
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            alignItems: "flex-start",
            marginLeft: "10px",
          }}
        >
          <Box sx={{ fontWeight: "bold" }}>Image</Box>
          <Box sx={{ fontSize: "11px" }}>Upload with a link.</Box>
        </Box>
      </Box>
    </Box>
  );
}
