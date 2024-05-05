import { useEffect, useRef, useState } from "react";
import {
  Modal,
  Paper,
  TextField,
  MenuItem,
  Button,
  Grid,
  Typography,
} from "@mui/material";
import { useQuery } from "react-query";
import { getConsumablesType } from "../service";

const AddConsumableModal = ({ onSubmit, consumablesInitial, ...props }) => {
  const [amount, setAmount] = useState(1);
  const consumablesType = useQuery("consumablesType", getConsumablesType);
  const typeSelector = useRef(null);
  const [consumables, setConsumables] = useState([]);

  useEffect(() => {
    if (Array.isArray(consumablesInitial)) {
      setConsumables(consumablesInitial);
    }
  }, [consumablesInitial]);

  const addOneConsumable = () => {
    const types = consumablesType.data;
    const selectId = typeSelector.current.value;
    const selectedType = types.find((item) => item.id === selectId);
    if (!selectedType) {
      return;
    }
    setConsumables((consumables) =>
      consumables.concat({
        coef: selectedType,
        amount,
      })
    );
    setAmount(1);
  };

  const deleteOneConsumableByIndex = (index) => {
    setConsumables((consumables) => consumables.filter((_, i) => i !== index));
  };

  const onFormSubmit = (e) => {
    e.preventDefault();
    onSubmit(consumables);
    props?.onClose();
  };

  return (
    <Modal {...props}>
      <Paper
        sx={{
          p: 2,
          maxWidth: 600,
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
        onSubmit={onFormSubmit}
      >
        <Grid container columnSpacing={1}>
          <Grid item container flexDirection="column" xs={9} rowSpacing={1}>
            <Grid item>
              <TextField
                defaultValue={1}
                label="Co2 source"
                select
                name="buildingType"
                size="small"
                inputRef={typeSelector}
                fullWidth
              >
                {consumablesType?.data?.map((el) => (
                  <MenuItem key={el.id + el.name} value={el.id}>
                    {el.name}
                  </MenuItem>
                ))}
              </TextField>
            </Grid>
            <Grid item>
              <TextField
                label="amount"
                name="amount"
                size="small"
                value={amount}
                onChange={(e) => setAmount(e.target.value)}
                fullWidth
              />
            </Grid>
          </Grid>
          <Grid item xs={3}>
            <Button variant="contained" onClick={addOneConsumable} fullWidth>
              Add
            </Button>
          </Grid>
        </Grid>
        <Grid>
          {consumables.map((el, i) => (
            <Grid
              key={el.id + el.amount}
              sx={{
                display: "flex",
                alignItems: "center",
                columnGap: 2,
                mb: "3px",
              }}
            >
              <Typography sx={{ color: "gray" }}>
                {i + 1}) {el.coef.name}: {el.amount}
              </Typography>
              <Button
                color="error"
                variant="contained"
                size="small"
                sx={{ p: 0 }}
                onClick={() => deleteOneConsumableByIndex(i)}
              >
                Delete
              </Button>
            </Grid>
          ))}
          {consumables.length === 0 && (
            <Typography sx={{ color: "gray", opacity: 0.2 }}>
              No sources...
            </Typography>
          )}
        </Grid>
        <Grid sx={{ display: "flex", justifyContent: "center" }}>
          <Button
            type="submit"
            variant="contained"
            disabled={consumables.length === 0}
          >
            Save
          </Button>
        </Grid>
      </Paper>
    </Modal>
  );
};

export default AddConsumableModal;
