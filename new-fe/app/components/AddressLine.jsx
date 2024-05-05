import { useEffect } from "react";
import toast from "react-hot-toast";
import { useMutation } from "react-query";
import { Typography, Box, Button, IconButton } from "@mui/material";
import HomeIcon from "@mui/icons-material/Home";
import CorporateFareIcon from "@mui/icons-material/CorporateFare";
import EditIcon from "@mui/icons-material/Edit";
import DeleteButton from "./DeleteButton";
import { deleteAddress } from "../service";
import { queryClient } from "../providers";
import { isHouse } from "../utils";

const AddressLine = ({ el, onAddExpenseClick, onEditClick }) => {
  const deleteMutation = useMutation("deleteAddress", deleteAddress);

  useEffect(() => {
    if (deleteMutation.isSuccess) {
      toast.success(`Address ${el.id} deleted`);
      queryClient.invalidateQueries({ queryKey: "userAddresses" });
    }
  }, [deleteMutation.isSuccess]);

  return (
    <Box
      sx={{
        display: "flex",
        alignItems: "center",
        flexDirection: "column",
        border: "1px solid",
        borderColor: "rgba(166, 166, 166, .3)",
        borderRadius: 3,
        mb: 0.5,
      }}
    >
      <Box
        sx={{
          display: "flex",
          width: "100%",
          alignItems: "center",
          justifyContent: "space-between",
          px: 1,
          color: "darkgray",
        }}
      >
        <Box sx={{ display: "flex", columnGap: 1, alignItems: "center" }}>
          <Box sx={{ display: "flex" }}>
            {isHouse(el.buildingType) ? <HomeIcon /> : <CorporateFareIcon />}
          </Box>
          <Typography>
            ({el.postalCode}) Country: {el.country}, city: {el.city}, street:{" "}
            {el.street}, house: {el.house}, flat: {el.flat}
          </Typography>
        </Box>
        <Box>
          <Button onClick={onAddExpenseClick}>CO2 Expenses</Button>
          <DeleteButton
            sx={{ opacity: 0.8 }}
            onClick={() => deleteMutation.mutate(el)}
          />
          <IconButton onClick={onEditClick}>
            <EditIcon />
          </IconButton>
        </Box>
      </Box>
    </Box>
  );
};

export default AddressLine;
