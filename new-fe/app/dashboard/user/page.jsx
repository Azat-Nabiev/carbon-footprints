"use client";
import { Fragment } from "react";
import { Container, Grid, Paper, Typography, Box } from "@mui/material";
import { useQuery } from "react-query";
import {
  getShortAddresses,
  getUserCarbonAmount,
  getUserConsumedResources,
  getUserProfileInfo,
} from "../../service";
import ShortAddressLine from "../../components/ShortAddressLine";
import { getDateFromArray } from "../../utils";

const Line = ({ value, label }) => {
  return (
    <Typography>
      <b>{label}</b>: {value}
    </Typography>
  );
};

const UserPage = () => {
  const { data, isLoading } = useQuery("userInfo", getUserProfileInfo);
  const shortAddreses = useQuery("userShortAddresses", getShortAddresses);
  const consumedResources = useQuery("userResources", getUserConsumedResources);
  const carbonAmount = useQuery("userCarbonAmount", getUserCarbonAmount);

  return (
    <Container sx={{ p: 3 }}>
      <Grid container spacing={2}>
        <Grid item xs={12} md={6}>
          <Paper sx={{ p: 2, width: "100%", borderRadius: 3 }}>
            <Typography
              sx={{ mb: 2, fontWeight: 600, fontSize: 20, opacity: 0.7 }}
            >
              General information
            </Typography>
            {isLoading && (
              <Typography sx={{ opacity: 0.2, fontSize: 20 }}>
                Loading...
              </Typography>
            )}
            {data && (
              <>
                <Line label="Name" value={data?.name} />
                <Line label="Lastname" value={data?.lastName} />
                <Line label="Email" value={data?.email} />
                <Line
                  label="Member since"
                  value={getDateFromArray(data?.createdDate)}
                />
              </>
            )}
          </Paper>
          <Paper
            sx={{
              mt: 2,
              width: "100%",
              gap: 4,
              display: "flex",
              flexDirection: "row",
              alignItems: "center",
            }}
          >
            <Box>
              <Typography
                variant="h5"
                sx={{
                  fontWeight: 600,
                  fontSize: 20,
                  opacity: 0.7,
                  color: "success.main",
                }}
              >
                Total produced:{" "}
                <span style={{ color: "black" }}>
                  {carbonAmount?.data?.produced}
                </span>
                <br />
                Your carbon footprint will be erased by{" "}
                <span style={{ color: "black" }}>
                  {(carbonAmount?.data?.produced / 50).toFixed(1)} hectares
                </span>{" "}
                of trees
              </Typography>
            </Box>
          </Paper>
        </Grid>
        <Grid
          item
          xs={12}
          md={6}
          sx={{ display: "flex", flexDirection: "column", rowGap: 2 }}
        >
          <Paper sx={{ width: "100%" }}>
            <Typography
              sx={{
                mb: 2,
                fontWeight: 600,
                fontSize: 20,
                opacity: 0.7,
                verticalAlign: "center",
              }}
            >
              Addresses{" "}
            </Typography>
            <Typography sx={{ mt: 2, opacity: 0.2 }}>
              {shortAddreses.data?.length === 0
                ? `No saved Addresses`
                : shortAddreses.isLoading
                ? "Loading..."
                : ""}
            </Typography>
            <Box sx={{ display: "flex", gap: 2, flexWrap: "wrap" }}>
              {shortAddreses?.data?.map((el) => (
                <ShortAddressLine key={el.id} el={el} />
              ))}
            </Box>
          </Paper>
          <Paper sx={{ width: "100%" }}>
            <Typography
              variant="h5"
              sx={{ fontWeight: 500, fontSize: 20, opacity: 0.7 }}
            >
              Consumed resourses:
              <br />
              {consumedResources?.data?.length === 0 && (
                <Typography sx={{ opacity: 0.2, mt: 2 }}>
                  No resourses
                </Typography>
              )}
              {consumedResources?.data?.map((el, i) => (
                <Fragment key={el}>
                  {i + 1}) {el.toLowerCase()}
                  <br />
                </Fragment>
              ))}
            </Typography>
          </Paper>
        </Grid>
      </Grid>
    </Container>
  );
};

export default UserPage;
