import { Button, Container, Stack, Typography } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../context/AuthContext";

export default function DashboardPage() {
  const { logout, user } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate("/", { replace: true });
  };

  return (
    <Container sx={{ mt: 8 }}>
      <Stack spacing={3}>

        <Typography variant="h3" fontWeight="bold">
          Dashboard
        </Typography>

        <Typography variant="body1">
          Bienvenido <strong>{user?.username}</strong>.
        </Typography>

        <Button
          variant="contained"
          color="error"
          onClick={handleLogout}
          sx={{ width: "fit-content" }}
        >
          Cerrar sesión
        </Button>

      </Stack>
    </Container>
  );
}