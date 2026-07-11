import { Routes, Route } from "react-router-dom";

import LoginPage from "../pages/auth/LoginPage";
import DashboardPage from "../pages/dashboard/DashboardPage";

import ProtectedRoute from "./ProtectedRoute";

export default function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<LoginPage />} />

      <Route
        path="/dashboard"
        element={
          <ProtectedRoute>
            <DashboardPage />
          </ProtectedRoute>
        }
      />
    </Routes>
  );
}