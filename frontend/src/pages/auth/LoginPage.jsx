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

import VisibilityRounded from "@mui/icons-material/VisibilityRounded";
import VisibilityOffRounded from "@mui/icons-material/VisibilityOffRounded";
import LoginRoundedIcon from "@mui/icons-material/LoginRounded";

import { Helmet } from "react-helmet-async";
import { useTranslation } from "react-i18next";

import AuthLayout from "../../layouts/AuthLayout";
import { login as loginService } from "../../services/authService";
import { useAuth } from "../../context/AuthContext";

export default function LoginPage() {
    const navigate = useNavigate();
    const { login } = useAuth();
    const { t } = useTranslation();

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [showPassword, setShowPassword] = useState(false);

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!username.trim() || !password.trim()) {
            setError(t("login.emptyFields"));
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
                t("login.invalidCredentials")
                    err.response?.data?.message ||
                    t("login.invalidCredentials")
            );
        } finally {
            setLoading(false);
        }
    };
 

    return (
        <AuthLayout>
            <Helmet>
                <title>{t("login.title")}</title>
            </Helmet>

            <Card
                elevation={6}
                sx={{
                    width: "100%",
                    maxWidth: 460,
                    p: 5,
                }}
            >
                <Stack spacing={4}>
                    <Box textAlign="center">
                        <Box
                            sx={{
                                width: 82,
                                height: 82,
                                borderRadius: "50%",
                                backgroundColor: "primary.main",
                                display: "flex",
                                justifyContent: "center",
                                alignItems: "center",
                                mx: "auto",
                                mb: 3,
                            }}
                        >
                            <LoginRoundedIcon
                                sx={{
                                    color: "#fff",
                                    fontSize: 42,
                                }}
                            />
                        </Box>

                        <Typography
                            variant="h4"
                            sx={{
                                textAlign: "center",
                                width: "100%",
                                fontWeight: 700,
                            }}
                        >
                        <Typography variant="h4">
                            {t("login.title")}
                        </Typography>

                        <Typography
                            color="text.secondary"
                            sx={{
                                mt: 1,
                                textAlign: "center",
                                width: "100%",
                                fontWeight: 700,
                            }}
                            sx={{ mt: 1 }}
                        >
                            {t("login.subtitle")}
                        </Typography>
                    </Box>

                    {error && (
                        <Alert severity="error">
                            {error}
                        </Alert>
                    )}

                    <Box
                        component="form"
                        onSubmit={handleSubmit}
                    >
                        <Stack spacing={3}>
                            <TextField
                                label={t("login.username")}
                                value={username}
                                autoComplete="username"
                                onChange={(e) =>
                                    setUsername(e.target.value)
                                }
                            />

                            <TextField
                                label={t("login.password")}
                                autoComplete="current-password"
                                type={
                                    showPassword
                                        ? "text"
                                        : "password"
                                }
                                value={password}
                                onChange={(e) =>
                                    setPassword(e.target.value)
                                }
                                slotProps={{
                                    input: {
                                        endAdornment: (
                                            <InputAdornment position="end">
                                                <IconButton
                                                    edge="end"
                                                    onClick={() =>
                                                        setShowPassword(
                                                            (prev) => !prev
                                                        )
                                                    }
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
                            >
                                {loading ? (
                                    <CircularProgress
                                        color="inherit"
                                        size={24}
                                    />
                                ) : (
                                    t("login.button")
                                )}
                            </Button>
                        </Stack>
                    </Box>
                </Stack>
            </Card>
        </AuthLayout>
    );
}