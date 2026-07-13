import { Box, Typography } from "@mui/material";

export default function Footer() {
  return (
    <Box
      component="footer"
      sx={{
        height: 56,
        px: 3,
        display: "flex",
        alignItems: "center",
        justifyContent: "space-between",
        borderTop: "1px solid",
        borderColor: "divider",
        bgcolor: "background.paper",
      }}
    >
      <Typography variant="body2" color="text.secondary">
        © {new Date().getFullYear()} Intranet Tools
      </Typography>

      <Typography variant="body2" color="text.secondary">
        v1.0.0
      </Typography>
    </Box>
  );
}