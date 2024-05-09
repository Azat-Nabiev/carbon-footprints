import { useEffect } from "react";
import { useFormik } from "formik";
import { Modal, Paper, TextField, MenuItem, Grid, Button } from "@mui/material";

const AddAdressModal = ({ inititalValues, onSubmit, onClose, ...props }) => {
  const form = useFormik({
    initialValues: {
      country: "",
      city: "",
      street: "",
      house: "",
      flat: "",
      postalCode: "",
      buildingType: "HOUSE_HOLD",
      carbon: [],
      status: "active",
    },
    onSubmit: (fields) => {
      form.resetForm();
      onSubmit(fields);
    },
  });

  useEffect(() => {
    if (Object.keys(inititalValues).length > 0) {
      form.setValues(inititalValues);
    }
  }, [inititalValues]);

  return (
    <Modal
      onClose={() => {
        onClose();
        form.resetForm();
      }}
      {...props}
    >
      <Paper
        sx={{
          p: 2,
          maxWidth: 400,
          width: "100%",
          position: "absolute",
          top: "50%",
          left: "50%",
          transform: "translate(-50%, -50%)",
          display: "flex",
          flexDirection: "column",
          gap: 2,
        }}
        component="form"
        onSubmit={form.handleSubmit}
      >
        <TextField
          label="Country"
          name="country"
          size="small"
          value={form.values.country}
          onChange={form.handleChange}
        />
        <Grid container spacing={1}>
          <Grid item xs={12} md={3}>
            <TextField
              label="City"
              name="city"
              size="small"
              value={form.values.city}
              onChange={form.handleChange}
            />
          </Grid>
          <Grid item xs={12} md={3}>
            <TextField
              label="Street"
              name="street"
              size="small"
              value={form.values.street}
              onChange={form.handleChange}
            />
          </Grid>
          <Grid item xs={12} md={3}>
            <TextField
              label="House"
              name="house"
              size="small"
              value={form.values.house}
              onChange={form.handleChange}
            />
          </Grid>
          <Grid item xs={12} md={3}>
            <TextField
              label="Flat"
              name="flat"
              size="small"
              value={form.values.flat}
              onChange={form.handleChange}
            />
          </Grid>
        </Grid>

        <TextField
          label="Postal code"
          name="postalCode"
          size="small"
          value={form.values.postalCode}
          onChange={form.handleChange}
        />
        <TextField
          select
          name="buildingType"
          size="small"
          defaultValue={form.values.buildingType}
          value={form.values.buildingType}
          onChange={form.handleChange}
        >
          <MenuItem value="HOUSE_HOLD">Household</MenuItem>
          <MenuItem value="ENTERPRISE">Enterprise</MenuItem>
        </TextField>
        <Button variant="contained" type="submit">
          {form.values.id ? "Change" : "Add"}
        </Button>
      </Paper>
    </Modal>
  );
};

export default AddAdressModal;