import { IconButton } from "@mui/material";
import DeleteIcon from "@mui/icons-material/Delete";
import React from "react";

const DeleteButton = ({ expanded, ...props }) => {
  return (
    <IconButton color="error" sx={{ p: 1, m: 0.5 }} {...props}>
      <DeleteIcon />
    </IconButton>
  );
};

export default DeleteButton;
