"use client";
import { Container, Grid, Typography, Paper } from "@mui/material";
import { useQuery } from "react-query";
import { getRating } from "../../service";
import Pedestal from "../../components/Pedestal";
import useUser from "../../hooks/useUser";
 
export default function Reg() {
  const { id } = useUser();
  const ratingQuery = useQuery("rating", getRating);
  const usersList = ratingQuery?.data?.usersRating;
  const user = Array.isArray(usersList)
    ? usersList.find((user) => user.id == id)
    : undefined;
 
  return (
    <Container sx={{ p: 2 }}>
      <Typography
        variant="h1"
        color="primary"
        sx={{ fontSize: 35, fontWeight: 600, mb: 2, textAlign: "center" }}
      >
        User score
      </Typography>
      <Pedestal users={usersList?.slice(0, 3)} />
      <Grid item xs={12} sx={{ mt: 2 }}>
        <Paper sx={{ p: 2, bgcolor: "primary.light", color: "white" }}>
          <Typography>
            {user?.firstName} {user?.lastName} your current position is{" "}
            {user?.position || "unknown"}
          </Typography>
        </Paper>
      </Grid>
      <Grid sx={{ mt: 2 }}>
        {usersList?.slice(3).map((user, i) => (
          <Grid
            key={user.id}
            sx={{
              borderRadius: 3,
              p: 1,
              display: "flex",
              border: "1px solid gray",
              mb: 1,
            }}
          >
            <Typography sx={{ fontWeight: 600 }}>№ {i + 4}</Typography>&nbsp;
            <Typography>
              {user?.firstName} {user?.lastName} (produced: {user?.produced})
            </Typography>
          </Grid>
        ))}
      </Grid>
    </Container>
  );
}
 