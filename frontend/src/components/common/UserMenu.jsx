import { useState } from "react";
import { useNavigate } from "react-router-dom";

import {
  Avatar,
  Divider,
  IconButton,
  ListItemIcon,
  Menu,
  MenuItem,
  Typography,
} from "@mui/material";

import PersonRoundedIcon from "@mui/icons-material/PersonRounded";
import LogoutRoundedIcon from "@mui/icons-material/LogoutRounded";
import SettingsRoundedIcon from "@mui/icons-material/SettingsRounded";

import { useAuth } from "../../context/AuthContext";
import { useTranslation } from "react-i18next";

export default function UserMenu() {
  const navigate = useNavigate();
  const { t } = useTranslation();

  const { user, logout } = useAuth();

  const [anchorEl, setAnchorEl] = useState(null);

  const open = Boolean(anchorEl);

  const handleOpen = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  return (
    <>
      <IconButton
        onClick={handleOpen}
        size="small"
      >
        <Avatar
          sx={{
            bgcolor: "primary.main",
            width: 40,
            height: 40,
            fontWeight: 600,
          }}
        >
          {user?.username?.charAt(0)?.toUpperCase() || "U"}
        </Avatar>
      </IconButton>

      <Menu
        anchorEl={anchorEl}
        open={open}
        onClose={handleClose}
        PaperProps={{
          sx: {
            mt: 1.5,
            width: 240,
            borderRadius: 3,
          },
        }}
      >
        <MenuItem
          disableRipple
          sx={{
            cursor: "default",
            display: "block",
            py: 2,
          }}
        >
          <Typography fontWeight={700}>
            {user?.username}
          </Typography>

          <Typography
            variant="body2"
            color="text.secondary"
          >
            {user?.email || ""}
          </Typography>
        </MenuItem>

        <Divider />

        <MenuItem
          onClick={() => {
            navigate("/profile");
            handleClose();
          }}
        >
          <ListItemIcon>
            <PersonRoundedIcon fontSize="small" />
          </ListItemIcon>

          {t("userMenu.profile")}
        </MenuItem>

        <MenuItem
          onClick={() => {
            navigate("/settings");
            handleClose();
          }}
        >
          <ListItemIcon>
            <SettingsRoundedIcon fontSize="small" />
          </ListItemIcon>

          {t("userMenu.settings")}
        </MenuItem>

        <Divider />

        <MenuItem
          onClick={handleLogout}
        >
          <ListItemIcon>
            <LogoutRoundedIcon
              color="error"
              fontSize="small"
            />
          </ListItemIcon>

          <Typography color="error">
            {t("userMenu.logout")}
          </Typography>
        </MenuItem>
      </Menu>
    </>
  );
}