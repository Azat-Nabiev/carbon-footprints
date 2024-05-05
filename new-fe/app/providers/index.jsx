"use client";
import { AppRouterCacheProvider } from "@mui/material-nextjs/v14-appRouter";
import { ThemeProvider } from "@mui/material";
import { createTheme } from "@mui/material/styles";
import { QueryClient, QueryClientProvider } from "react-query";
import { UserContextProvider } from "../ctx/UserCtx";

const theme = createTheme({
  typography: {
    fontFamily: [
      "-apple-system",
      "BlinkMacSystemFont",
      '"Segoe UI"',
      "Roboto",
      '"Helvetica Neue"',
      "Arial",
      "sans-serif",
      '"Apple Color Emoji"',
      '"Segoe UI Emoji"',
      '"Segoe UI Symbol"',
    ].join(","),
  },
  palette: {
    primary: {
      light: "#81c784",
      dark: "#388e3c",
      main: "#4caf50",
      contrastText: "white",
    },
  },
  components: {
    MuiPaper: {
      styleOverrides: {
        root: {
          padding: "10px 15px",
          borderRadius: "10px",
        },
      },
    },
  },
  props: {
    MuiButton: {
      size: "small",
    },
  },
});
export const queryClient = new QueryClient();

const Providers = ({ children }) => {
  return (
    <AppRouterCacheProvider options={{ key: "css" }}>
      <QueryClientProvider client={queryClient}>
        <ThemeProvider theme={theme}>
          <UserContextProvider>{children}</UserContextProvider>
        </ThemeProvider>
      </QueryClientProvider>
    </AppRouterCacheProvider>
  );
};

export default Providers;
