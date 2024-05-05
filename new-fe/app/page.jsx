"use client";
import { useFormik } from "formik";
import { useMutation } from "react-query";
import { useEffect } from "react";
import toast from "react-hot-toast";
import { useRouter } from "next/navigation";
import LoadingButton from "@mui/lab/LoadingButton";
import { Paper, TextField, Typography } from "@mui/material";
import { login } from "./service";
import {
  saveAccessToken,
  saveRefreshToken,
  saveUserId,
} from "./service/storage";
import useUser from "./hooks/useUser";
import { links } from "./components/Navbar";

export default function Login() {
  const router = useRouter();
  const { toggleAccessCheck } = useUser();
  const { data, mutate, isLoading, isError, isSuccess } = useMutation(
    "login",
    login
  );
  const form = useFormik({
    initialValues: {
      email: "",
      password: "",
    },
    onSubmit: mutate,
  });

  useEffect(() => {
    if (isSuccess) {
      toast.success("login success");
      if (data) {
        saveUserId(data.userId);
        saveAccessToken(data.access_token);
        saveRefreshToken(data.refresh_token);
        toggleAccessCheck();
        router.push(links.user);
      }
    }
    if (isError) {
      toast.error("wrong creds");
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
      <Typography sx={{ fontWeight: 600 }}>Login</Typography>
      <TextField
        name="email"
        label="email"
        size="small"
        value={form.values.email}
        onChange={form.handleChange}
      />
      <TextField
        name="password"
        label="password"
        size="small"
        value={form.values.password}
        onChange={form.handleChange}
      />
      <LoadingButton loading={isLoading} variant="contained" type="submit">
        Enter
      </LoadingButton>
    </Paper>
  );
}
