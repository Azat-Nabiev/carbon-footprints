"use client";
import { useEffect } from "react";
import useUser from "../hooks/useUser";
import { getAccessToken } from "../service/storage";

export default function DashboardLayout({ children }) {
  const { logout } = useUser();

  useEffect(() => {
    try {
      getAccessToken();
    } catch {
      logout();
    }
  }, []);

  return children;
}
