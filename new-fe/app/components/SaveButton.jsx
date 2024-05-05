import { IconButton } from "@mui/material";
import SaveIcon from "@mui/icons-material/Save";
import React from "react";

const DeleteButton = ({ expanded, ...props }) => {
  return (
    <IconButton color="primary" sx={{ p: 1, m: 0.5 }} {...props}>
      <SaveIcon />
    </IconButton>
  );
};

export default DeleteButton;
