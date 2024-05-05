"use client";
import { AppBar, Box, Toolbar, Typography, Button } from "@mui/material";
import Link from "./Link";
import { usePathname } from "next/navigation";
import Tree from "./Tree";
import useUser from "../hooks/useUser";
import "@fontsource/space-mono/700.css";

export const links = {
  login: "/",
  reg: "/reg",
  user: "/dashboard/user",
  rating: "/dashboard/rating",
  calc: "/dashboard/calc",
};

const Navbar = () => {
  const { authorized, logout } = useUser();
  const pathname = usePathname();

  return (
    <AppBar position="fixed" sx={{ boxShadow: "none", borderRadius: 0 }}>
      <Toolbar
        variant="regular"
        sx={{ display: "flex", justifyContent: "space-between" }}
      >
        <Typography
          variant="h1"
          color="inherit"
          sx={{
            display: "flex",
            alignItems: "center",
            fontFamily: "Space Mono",
            verticalAlign: "middle",
            gap: 1,
            fontSize: 20,
            mr: 1,
          }}
        >
          <Tree fill="white" />
          CarbonFootprint
        </Typography>
        <Box component="nav" sx={{ display: "flex", gap: 2 }}>
          {!authorized && (
            <>
              <Link
                href={links.login}
                active={pathname === links.login}
                disabled={pathname == links.login}
              >
                Login
              </Link>
              <Link
                href={links.reg}
                active={pathname === links.reg}
                disabled={pathname == links.reg}
              >
                Registration
              </Link>
            </>
          )}
          {authorized && (
            <>
              <Link
                href={links.rating}
                active={pathname === links.rating}
                disabled={pathname == links.rating}
              >
                Rating
              </Link>
              <Link
                href={links.calc}
                active={pathname === links.calc}
                disabled={pathname == links.calc}
              >
                Calculator
              </Link>
              <Link
                href={links.user}
                active={pathname === links.user}
                disabled={pathname == links.user}
              >
                User
              </Link>
              <a className="link" onClick={logout}>
                Logout
              </a>
            </>
          )}
        </Box>
      </Toolbar>
    </AppBar>
  );
};

export default Navbar;
