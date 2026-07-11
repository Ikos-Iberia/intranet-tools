import { useState } from "react";
import { useNavigate } from "react-router-dom";

import {
  Box,
  Button,
  Card,
  CardContent,
  Container,
  TextField,
  Typography,
  Alert,
} from "@mui/material";

import { login as loginService } from "../../services/authService";
import { useAuth } from "../../context/AuthContext";

export default function LoginPage() {
  const navigate = useNavigate();
  const { login } = useAuth();

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    setError("");
    setLoading(true);

    try {
      const data = await loginService({
        username,
        password,
      });

      login(data);

      navigate("/dashboard");
    } catch (err) {
      setError(
        err.response?.data?.error ||
          "Usuario o contraseña incorrectos."
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container maxWidth="sm">
      <Box
        sx={{
          minHeight: "100vh",
          display: "flex",
          alignItems: "center",
        }}
      >
        <Card sx={{ width: "100%", p: 2 }}>
          <CardContent>

            <Typography
              variant="h4"
              textAlign="center"
              gutterBottom
            >
              Intranet Tools
            </Typography>

            <Typography
              textAlign="center"
              color="text.secondary"
              mb={4}
            >
              Inicia sesión para continuar
            </Typography>

            {error && (
              <Alert severity="error" sx={{ mb: 2 }}>
                {error}
              </Alert>
            )}

            <Box
              component="form"
              onSubmit={handleSubmit}
            >

              <TextField
                label="Usuario"
                fullWidth
                margin="normal"
                value={username}
                onChange={(e) =>
                  setUsername(e.target.value)
                }
              />

              <TextField
                label="Contraseña"
                type="password"
                fullWidth
                margin="normal"
                value={password}
                onChange={(e) =>
                  setPassword(e.target.value)
                }
              />

              <Button
                type="submit"
                variant="contained"
                fullWidth
                size="large"
                sx={{ mt: 3 }}
                disabled={loading}
              >
                {loading
                  ? "Iniciando..."
                  : "Iniciar sesión"}
              </Button>

            </Box>

          </CardContent>
        </Card>
      </Box>
    </Container>
  );
}