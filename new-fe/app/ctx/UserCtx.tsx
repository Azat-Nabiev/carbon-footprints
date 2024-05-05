"use client";
import { createContext, useEffect, useState, useContext } from "react";
import { getAccessToken, cleanAuthData, getSavedId } from "../service/storage";
import { useRouter } from "next/navigation";

const USER_INITIAL = {
  id: null,
  authorized: false,
};

const USER_INITAL_CTX = {
  ...USER_INITIAL,
  logout: () => {},
  toggleAccessCheck: () => {},
};

const userContext = createContext(USER_INITAL_CTX);
export default userContext;

export const UserContextProvider = ({ children }) => {
  const [user, setUser] = useState(USER_INITIAL);
  const router = useRouter();

  const toggleAccessCheck = () => {
    try {
      const authorized = Boolean(getAccessToken());
      const id = getSavedId();
      setUser({ id, authorized });
    } catch {
      console.log("User creds erased or not persist");
      setUser(USER_INITIAL);
    }
  };

  useEffect(toggleAccessCheck, []);

  const logout = () => {
    cleanAuthData();
    setUser(USER_INITIAL);
    router.push("/");
  };

  return (
    <userContext.Provider value={{ ...user, logout, toggleAccessCheck }}>
      {children}
    </userContext.Provider>
  );
};
