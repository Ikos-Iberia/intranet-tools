import React from "react";
import ReactDOM from "react-dom/client";

import { BrowserRouter } from "react-router-dom";

import { ThemeProvider } from "@mui/material/styles";
import CssBaseline from "@mui/material/CssBaseline";

import "@fontsource/inter";

import App from "./App";
import theme from "./theme";
import "./i18n";

import { AuthProvider } from "./context/AuthContext";
import { HelmetProvider } from "react-helmet-async";

import "./index.css";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <HelmetProvider>
      <ThemeProvider theme={theme}>
        <CssBaseline />

        <BrowserRouter>
          <AuthProvider>
            <App />
          </AuthProvider>
        </BrowserRouter>

      </ThemeProvider>
    </HelmetProvider>
  </React.StrictMode>
);