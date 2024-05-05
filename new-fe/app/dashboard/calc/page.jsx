"use client";
import { useState, useEffect } from "react";
import { LoadingButton } from "@mui/lab";
import toast from "react-hot-toast";
import { useMutation, useQuery } from "react-query";
import { Container, Grid, Paper, Typography, Button } from "@mui/material";
import FileDownloadIcon from "@mui/icons-material/FileDownload";
import AddressLine from "../../components/AddressLine";
import AddAddressModal from "../../components/AddAddressModal";
import AddConsumableModal from "../../components/AddConsumableModal";
import {
  addAddress,
  changeAddress,
  getAddressReport,
  getUserAddresses,
} from "../../service";
import { queryClient } from "../../providers";

export default function CalcPage() {
  const addAddressMutation = useMutation("addAddress", addAddress);
  const changeAddressMutation = useMutation("changeAddress", changeAddress);
  const getReport = useMutation("addressReport", getAddressReport);
  const [consumableEl, setConsumableEl] = useState({});
  const [consumbaleModalOpen, setConsumableModalOpen] = useState(false);
  const [addModalOpen, setAddModalOpen] = useState(false);
  const addressEvent = useQuery("userAddresses", getUserAddresses);

  const getAddCo2ExpenseHandler = (el) => () => {
    setConsumableModalOpen(true);
    setConsumableEl(el);
  };

  const getOnEditClickHandler = (el) => () => {
    setConsumableEl(el);
    setAddModalOpen(true);
  };

  const onAddAddresClick = () => {
    setConsumableEl({});
    setAddModalOpen(true);
  };

  const onAddFormSubmit = (fields) => {
    if (fields.id) {
      changeAddressMutation.mutate(fields);
    } else {
      addAddressMutation.mutate({ ...fields, carbon: [], status: "ACTIVE" });
    }
    setConsumableEl({});
    setAddModalOpen(false);
  };

  const onConsumableSubmit = (carbon) => {
    const addressWithCarbon = { ...consumableEl, carbon };
    changeAddressMutation.mutate(addressWithCarbon);
  };

  useEffect(() => {
    if (changeAddressMutation.isSuccess) {
      queryClient.invalidateQueries({ queryKey: "userAddresses" });
      toast.success(`Update success`);
    }
  }, [changeAddressMutation.isSuccess]);

  useEffect(() => {
    if (!addAddressMutation.isSuccess && !addAddressMutation.isError) {
      return;
    }
    if (addAddressMutation.isSuccess) {
      toast.success("Address added");
    }
    if (addAddressMutation.isError) {
      toast.error("Error hapened");
    }
    queryClient.invalidateQueries({ queryKey: "userAddresses" });
  }, [addAddressMutation.isSuccess, addAddressMutation.isError]);

  return (
    <Container>
      <AddConsumableModal
        consumablesInitial={consumableEl.carbon}
        onSubmit={onConsumableSubmit}
        open={consumbaleModalOpen}
        onClose={() => setConsumableModalOpen(false)}
      />
      <AddAddressModal
        inititalValues={consumableEl}
        onSubmit={onAddFormSubmit}
        open={addModalOpen}
        onClose={() => setAddModalOpen(false)}
      />
      <Grid container justifyContent="center">
        <Grid item xs={10}>
          <Paper sx={{ p: 2, width: "100%", borderRadius: 3 }}>
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
              <Button
                onClick={onAddAddresClick}
                sx={{ mx: 2 }}
                variant="contained"
              >
                Add address
              </Button>
              <LoadingButton
                endIcon={<FileDownloadIcon />}
                onClick={() => getReport.mutate()}
                variant="contained"
                download
              >
                Download report
              </LoadingButton>
            </Typography>
            <Typography sx={{ mt: 4, opacity: 0.2 }}>
              {addressEvent.data?.length === 0
                ? `No saved Addresses`
                : addressEvent.isLoading
                ? "Loading..."
                : ""}
            </Typography>
            {addressEvent?.data?.map((el) => (
              <AddressLine
                key={el.id}
                el={el}
                onAddExpenseClick={getAddCo2ExpenseHandler(el)}
                onEditClick={getOnEditClickHandler(el)}
              />
            ))}
          </Paper>
        </Grid>
      </Grid>
    </Container>
  );
}
