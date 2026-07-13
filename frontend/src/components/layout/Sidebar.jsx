import { NavLink } from "react-router-dom";

import {
  Box,
  Drawer,
  List,
  ListItemButton,
  ListItemIcon,
  ListItemText,
  Toolbar,
} from "@mui/material";

import { useTranslation } from "react-i18next";

import navigation from "../../config/navigation";

const drawerWidth = 260;

export default function Sidebar({
  open,
  mobile,
  onClose,
}) {
  const { t } = useTranslation();

  return (
    <Drawer
      variant={mobile ? "temporary" : "permanent"}
      open={mobile ? open : true}
      onClose={onClose}
      sx={{
        width: open ? drawerWidth : 72,
        flexShrink: 0,

        "& .MuiDrawer-paper": {
          width: open ? drawerWidth : 72,
          transition: "width .25s",
          overflowX: "hidden",
          boxSizing: "border-box",
          borderRight: 1,
          borderColor: "divider",
          bgcolor: "background.paper",
        },
      }}
    >
      <Toolbar />

      <Box sx={{ py: 2 }}>
        <List>
          {navigation
            .filter((item) => item.showInSidebar)
            .map((item) => {
              const Icon = item.icon;

              return (
                <ListItemButton
                  key={item.id}
                  component={NavLink}
                  to={item.path}
                  sx={{
                    mx: 1.5,
                    mb: .5,
                    borderRadius: 2,
                    justifyContent: open ? "initial" : "center",

                    "&.active": {
                      bgcolor: "primary.main",
                      color: "#fff",

                      "& .MuiListItemIcon-root": {
                        color: "#fff",
                      },
                    },
                  }}
                >
                  <ListItemIcon>
                    <Icon />
                  </ListItemIcon>

                  <ListItemText
                    primary={t(item.title)}
                  />
                </ListItemButton>
              );
            })}
        </List>
      </Box>
    </Drawer>
  );
}