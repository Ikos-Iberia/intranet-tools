import { useState } from "react";
import { useNavigate } from "react-router-dom";

import {
    Alert,
    Box,
    Button,
    Card,
    CircularProgress,
    IconButton,
    InputAdornment,
    Stack,
    TextField,
    Typography,
} from "@mui/material";

import PersonRoundedIcon from "@mui/icons-material/PersonRounded";
import LockRoundedIcon from "@mui/icons-material/LockRounded";
import VisibilityRounded from "@mui/icons-material/VisibilityRounded";
import VisibilityOffRounded from "@mui/icons-material/VisibilityOffRounded";
import LoginRoundedIcon from "@mui/icons-material/LoginRounded";

import { login as loginService } from "../../services/authService";
import { useAuth } from "../../context/AuthContext";

export default function LoginPage() {
    const navigate = useNavigate();
    const { login } = useAuth();

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const [showPassword, setShowPassword] = useState(false);

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!username.trim() || !password.trim()) {
            setError("Debes introducir usuario y contraseña.");
            return;
        }

        try {
            setLoading(true);
            setError("");

            const response = await loginService({
                username,
                password,
            });

            login(response);

            navigate("/dashboard");
        } catch (err) {
            setError(
                err.response?.data?.error ||
                err.response?.data?.message ||
                "Usuario o contraseña incorrectos."
            );
        } finally {
            setLoading(false);
        }
    };

    return (
        <Box
            sx={{
                minHeight: "100vh",
                width: "100%",
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                px: 2,
                background:
                    "linear-gradient(135deg,#1F4F8F 0%, #2D6AB3 45%, #6FA8DC 100%)",
            }}
        >
            <Card
                elevation={15}
                sx={{
                    width: "100%",
                    maxWidth: 460,
                    borderRadius: 5,
                    p: 5,
                    backdropFilter: "blur(12px)",
                    backgroundColor: "rgba(255,255,255,.96)",
                }}
            >
                <Stack spacing={4}>
                    <Box textAlign="center">
                        <Box
                            sx={{
                                width: 82,
                                height: 82,
                                borderRadius: "50%",
                                backgroundColor: "#1F4F8F",
                                display: "flex",
                                justifyContent: "center",
                                alignItems: "center",
                                margin: "0 auto",
                                mb: 3,
                            }}
                        >
                            <LoginRoundedIcon
                                sx={{
                                    color: "white",
                                    fontSize: 42,
                                }}
                            />
                        </Box>

                        <Typography variant="h4" fontWeight={700} sx={{ textAlign: "center", width: "100%" }}>
                            Intranet Tools
                        </Typography>

                        <Typography
                            color="text.secondary"
                            sx={{
                                mt: 1.5,
                                textAlign: "center",
                            }}
                        >
                            Inicia sesión para acceder a la plataforma
                        </Typography>
                    </Box>

                    {error && <Alert severity="error">{error}</Alert>}

                    <Box component="form" onSubmit={handleSubmit}>
                        <Stack spacing={3}>
                            <TextField
                                label="Usuario"
                                fullWidth
                                value={username}
                                autoComplete="username"
                                onChange={(e) => setUsername(e.target.value)}
                            />

                            <TextField
                                label="Contraseña"
                                fullWidth
                                autoComplete="current-password"
                                type={showPassword ? "text" : "password"}
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                slotProps={{
                                    input: {

                                        endAdornment: (
                                            <InputAdornment position="end">
                                                <IconButton
                                                    edge="end"
                                                    onClick={() => setShowPassword((prev) => !prev)}
                                                    sx={{
                                                        color: "primary.main",
                                                    }}
                                                >
                                                    {showPassword ? (
                                                        <VisibilityOffRounded />
                                                    ) : (
                                                        <VisibilityRounded />
                                                    )}
                                                </IconButton>
                                            </InputAdornment>
                                        ),
                                    },
                                }}
                            />
                            <Button
                                type="submit"
                                variant="contained"
                                size="large"
                                disabled={loading}
                                sx={{
                                    height: 52,
                                    borderRadius: 3,
                                    fontWeight: 700,
                                    fontSize: 16,
                                    textTransform: "none",
                                    boxShadow: 4,
                                }}
                            >
                                {loading ? (
                                    <CircularProgress color="inherit" size={24} />
                                ) : (
                                    "Iniciar sesión"
                                )}
                            </Button>
                        </Stack>
                    </Box>
                </Stack>
            </Card>
        </Box>
    );
}