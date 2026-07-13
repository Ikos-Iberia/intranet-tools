import { Box } from "@mui/material";

import { useEffect, useState } from "react";
import { useMediaQuery, useTheme } from "@mui/material";

import Header from "../components/layout/Header";
import Sidebar from "../components/layout/Sidebar";
import Footer from "../components/layout/Footer";

export default function MainLayout({ children }) {

  const theme = useTheme();

  const isMobile = useMediaQuery(theme.breakpoints.down("md"));

  const [sidebarOpen, setSidebarOpen] = useState(true);

  useEffect(() => {
    setSidebarOpen(!isMobile);
  }, [isMobile]);

  return (
    <Box
      sx={{
        display: "flex",
        minHeight: "100vh",
      }}
    >
      <Sidebar
        open={sidebarOpen}
        mobile={isMobile}
        onClose={() => setSidebarOpen(false)}
      />

      <Box
        sx={{
          flex: 1,
          display: "flex",
          flexDirection: "column",
          minWidth: 0,
        }}
      >
        <Header
          sidebarOpen={sidebarOpen}
          setSidebarOpen={setSidebarOpen}
          mobile={isMobile}
        />

        <Box
          component="main"
          sx={{
            flex: 1,
            p: 4,
          }}
        >
          {children}
        </Box>

        <Footer />
      </Box>
    </Box>
  );
}