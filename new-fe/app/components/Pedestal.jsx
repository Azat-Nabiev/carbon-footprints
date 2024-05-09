import { Grid, Box, Typography } from "@mui/material";

const maxHeight = 500;
const width = 200;

const User = ({ user }) => (
  <Box
    sx={{
      top: "100px",
      display: "flex",
      position: "absolute",
      top: "-55px",
      left: "50px",
      display: "flex",
      flexDirection: "column",
      alignItems: "center",
      zIndex: 20,
      width: 100,
    }}
  >
    <Typography sx={{ whiteSpace: "nowrap", textAlign: "center" }}>
      {user.firstName} {user.lastName}
    </Typography>
    <Typography sx={{ whiteSpace: "nowrap", textAlign: "center" }}>
      (produced: {user.produced} tons)
    </Typography>
  </Box>
);

const Pedestal = ({ users }) => {
  if (!users) {
    return <></>;
  }
  return (
    <Grid
      sx={{
        maxHeight: maxHeight * 1.5,
        display: "flex",
        alignItems: "flex-end",
        justifyContent: "center",
        height: "100%",
        bgcolor: "rgba(150, 169, 255, 0.1)",
        borderRadius: 3,
        p: 10,
      }}
    >
      <Box
        sx={{
          border: "1px solid gray",
          width,
          height: maxHeight / 2,
          borderRight: "none",
          bgcolor: "white",
          position: "relative",
        }}
      >
        <Typography
          sx={{
            textAlign: "center",
            fontSize: 40,
            fontWeight: 800,
          }}
        >
          2
        </Typography>
        {users[1] && <User user={users[1]} />}
      </Box>
      <Box
        sx={{
          border: "1px solid gray",
          bgcolor: "white",
          position: "relative",
          width,
          height: maxHeight,
        }}
      >
        <Typography sx={{ textAlign: "center", fontSize: 40, fontWeight: 800 }}>
          1
        </Typography>
        {users[0] && <User user={users[0]} />}
      </Box>
      <Box
        sx={{
          border: "1px solid gray",
          width,
          height: maxHeight / 4,
          borderLeft: "none",
          bgcolor: "white",
          position: "relative",
        }}
      >
        <Typography sx={{ textAlign: "center", fontSize: 40, fontWeight: 800 }}>
          3
        </Typography>
        {users[2] && <User user={users[2]} />}
      </Box>
    </Grid>
  );
};

export default Pedestal;