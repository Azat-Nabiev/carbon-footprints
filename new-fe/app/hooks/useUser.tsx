import { useContext } from "react";
import userContext from "../ctx/UserCtx";

const useUser = () => useContext(userContext);

export default useUser;
