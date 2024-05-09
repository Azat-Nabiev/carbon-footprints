"use client";
import { useEffect } from "react";
import { useFormik } from "formik";
import { useMutation } from "react-query";
import toast from "react-hot-toast";
import { useRouter } from "next/navigation";
import { Paper, TextField, Typography } from "@mui/material";
import LoadingButton from "@mui/lab/LoadingButton";
import { register } from "../service";

export default function Reg() {
  const router = useRouter();
  const { isLoading, isSuccess, isError, mutate } = useMutation(
    "register",
    register
  );
  const form = useFormik({
    initialValues: {
      firstName: "",
      lastName: "",
      email: "",
      password: "",
    },
    onSubmit: mutate,
  });

  useEffect(() => {
    if (isSuccess) {
      toast.success("registration success");
      setTimeout(() => router.push("/"), 1500);
    }
    if (isError) {
      toast.error("error happened");
    }
  }, [isSuccess, isError]);

  return (
    <Paper
      sx={{
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
      <Typography sx={{ fontWeight: 600, mb: 2 }}>Registration</Typography>
      <TextField
        name="firstName"
        label="First name"
        size="small"
        value={form.values.firstName}
        onChange={form.handleChange}
      />
      <TextField
        name="lastName"
        label="Last name"
        size="small"
        value={form.values.lastName}
        onChange={form.handleChange}
      />
      <TextField
        label="Email"
        name="email"
        size="small"
        value={form.values.email}
        onChange={form.handleChange}
      />
      <TextField
        label="Password"
        name="password"
        size="small"
        value={form.values.password}
        onChange={form.handleChange}
      />
      <LoadingButton loading={isLoading} variant="contained" type="submit">
        submit
      </LoadingButton>
    </Paper>
  );
}

