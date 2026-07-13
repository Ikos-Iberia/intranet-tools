import DashboardRoundedIcon from "@mui/icons-material/DashboardRounded";
import PictureAsPdfRoundedIcon from "@mui/icons-material/PictureAsPdfRounded";
import LockRoundedIcon from "@mui/icons-material/LockRounded";
import InventoryRoundedIcon from "@mui/icons-material/InventoryRounded";
import SettingsRoundedIcon from "@mui/icons-material/SettingsRounded";

const navigation = [
  {
    id: "dashboard",
    title: "navigation.dashboard",
    description: "navigation.dashboardDescription",
    icon: DashboardRoundedIcon,
    color: "primary.main",
    path: "/dashboard",
    showInSidebar: true,
    showOnDashboard: false,
  },

  {
    id: "pdf",
    title: "navigation.pdf",
    description: "navigation.pdfDescription",
    icon: PictureAsPdfRoundedIcon,
    color: "#E53935",
    path: "/tools/pdf",
    showInSidebar: true,
    showOnDashboard: true,
  },

  {
    id: "passwords",
    title: "navigation.passwords",
    description: "navigation.passwordsDescription",
    icon: LockRoundedIcon,
    color: "#3949AB",
    path: "/tools/passwords",
    showInSidebar: true,
    showOnDashboard: true,
  },

  {
    id: "inventory",
    title: "navigation.inventory",
    description: "navigation.inventoryDescription",
    icon: InventoryRoundedIcon,
    color: "#43A047",
    path: "/tools/inventory",
    showInSidebar: true,
    showOnDashboard: true,
  },

  {
    id: "settings",
    title: "navigation.settings",
    description: "navigation.settingsDescription",
    icon: SettingsRoundedIcon,
    color: "#757575",
    path: "/settings",
    showInSidebar: true,
    showOnDashboard: false,
  },
];

export default navigation;