import { Box } from "@mui/material";
import LanguageSelector from "../components/common/LanguageSelector";

export default function AuthLayout({ children }) {
  return (
    <Box
      sx={{
        minHeight: "100vh",
        width: "100%",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        position: "relative",
        px: 2,
        background:
          "linear-gradient(135deg,#1F4F8F 0%, #2D6AB3 45%, #6FA8DC 100%)",
      }}
    >
      <Box
        sx={{
          position: "absolute",
          top: 20,
          right: 20,
        }}
      >
        <LanguageSelector />
      </Box>

      {children}
    </Box>
  );
}