import { CssBaseline } from "@mui/material";
import Providers from "./providers";
import { Toaster } from "react-hot-toast";
import { Box } from "@mui/material";
import Navbar from "./components/Navbar";
import "./main.css";

export const metadata = {
  title: "Carbon-footprints",
  description: "Controll your pollution",
};

export default function RootLayout({ children }) {
  return (
    <html lang="en">
      <body>
        <Toaster position="bottom-right" />
        <Providers>
          <CssBaseline />
          <Navbar />
          <Box component="main" sx={{ pt: "100px" }}>
            {children}
          </Box>
        </Providers>
      </body>
    </html>
  );
}
