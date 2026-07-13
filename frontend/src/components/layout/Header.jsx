import { useNavigate } from "react-router-dom";
import { useTranslation } from "react-i18next";

import {
  AppBar,
  Toolbar,
  Typography,
  Box,
  Avatar,
  IconButton,
  Menu,
  MenuItem,
  Divider,
  Tooltip,
} from "@mui/material";

import LogoutRoundedIcon from "@mui/icons-material/LogoutRounded";
import PersonRoundedIcon from "@mui/icons-material/PersonRounded";
import MenuRoundedIcon from "@mui/icons-material/MenuRounded";
import AccountCircleRoundedIcon from "@mui/icons-material/AccountCircleRounded";

import { useAuth } from "../../context/AuthContext";
import LanguageSelector from "../common/LanguageSelector";

import { useState } from "react";

export default function Header({ sidebarOpen, setSidebarOpen, mobile }) {
  const navigate = useNavigate();
  const { t } = useTranslation();
  const { user, logout } = useAuth();

  const [anchorEl, setAnchorEl] = useState(null);

  const open = Boolean(anchorEl);

  const handleOpenMenu = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleCloseMenu = () => {
    setAnchorEl(null);
  };

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  return (
    <AppBar
      position="sticky"
      elevation={1}
      color="inherit"
      sx={{
        borderBottom: "1px solid",
        borderColor: "divider",
        bgcolor: "background.paper",
      }}
    >
      <Toolbar
        sx={{
          justifyContent: "space-between",
          minHeight: 72,
        }}
      >

        {/* Logo */}

        <Box
          sx={{
            display: "flex",
            alignItems: "center",
            gap: 2,
          }}
        >

          <IconButton
            onClick={() => setSidebarOpen(!sidebarOpen)}
            sx={{ mr: 2 }}
          >
            <MenuRoundedIcon />
          </IconButton>

          <Avatar
            sx={{
              bgcolor: "primary.main",
              width: 42,
              height: 42,
            }}
          >
            <PersonRoundedIcon />
          </Avatar>

          <Typography
            variant="h6"
            fontWeight={700}
            color="text.primary"
          >
            Intranet Tools
          </Typography>
        </Box>

        {/* Parte derecha */}

        <Box
          sx={{
            display: "flex",
            alignItems: "center",
            gap: 2,
          }}
        >
          <LanguageSelector />

          <Tooltip title={user?.username || ""}>
            <IconButton onClick={handleOpenMenu}>
              <Avatar
                sx={{
                  bgcolor: "primary.main",
                }}
              >
                {(user?.username || "U")
                  .charAt(0)
                  .toUpperCase()}
              </Avatar>
            </IconButton>
          </Tooltip>

          <Menu
            anchorEl={anchorEl}
            open={open}
            onClose={handleCloseMenu}
          >
            <MenuItem disabled>
              <AccountCircleRoundedIcon
                fontSize="small"
                sx={{ mr: 1 }}
              />

              {user?.username}
            </MenuItem>

            <Divider />

            <MenuItem
              onClick={handleLogout}
            >
              <LogoutRoundedIcon
                fontSize="small"
                sx={{ mr: 1 }}
              />

              {t("header.logout")}
            </MenuItem>
          </Menu>
        </Box>
      </Toolbar>
    </AppBar>
  );
}