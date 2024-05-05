import { Typography, Box } from "@mui/material";
import HomeIcon from "@mui/icons-material/Home";
import CorporateFareIcon from "@mui/icons-material/CorporateFare";
import { isHouse } from "../utils";

const ShortAddressLine = ({ el }) => {
  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        border: "1px solid",
        borderRadius: 2,
        borderColor: "primary.main",
        py: 0.5,
        px: 1,
        minWidth: 170,
      }}
    >
      <Typography sx={{ color: "primary.main" }}>
        {isHouse(el.buildingType) ? <HomeIcon /> : <CorporateFareIcon />}
      </Typography>
      <Typography>Country: {el.country}</Typography>
      <Typography>
        City: {el.city} ({el.postalCode})
      </Typography>
      <Typography>
        House: {el.house}, Flat: {el.flat}
      </Typography>
    </Box>
  );
};

export default ShortAddressLine;
