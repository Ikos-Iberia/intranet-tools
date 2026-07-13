import { useMemo, useState } from "react";

import {
  Grid,
  Stack,
  Typography,
} from "@mui/material";

import AppsRoundedIcon from "@mui/icons-material/AppsRounded";
import BuildRoundedIcon from "@mui/icons-material/BuildRounded";
import DashboardRoundedIcon from "@mui/icons-material/DashboardRounded";

import { Helmet } from "react-helmet-async";
import { useTranslation } from "react-i18next";

import MainLayout from "../../layouts/MainLayout";

import SearchBar from "../../components/common/SearchBar";
import StatCard from "../../components/ui/StatCard";
import AppCard from "../../components/ui/AppCard";

import navigation from "../../config/navigation";

export default function DashboardPage() {
  const { t } = useTranslation();

  const [search, setSearch] = useState("");

  const apps = useMemo(() => {
    return navigation.filter((item) => {
      if (!item.showOnDashboard) return false;

      return t(item.title)
        .toLowerCase()
        .includes(search.toLowerCase());
    });
  }, [search, t]);

  return (
    <MainLayout>
      <Helmet>
        <title>{t("dashboard.title")}</title>
      </Helmet>

      <Stack spacing={4}>
        <div>
          <Typography variant="h4" fontWeight={700}>
            {t("dashboard.title")}
          </Typography>

          <Typography color="text.secondary">
            {t("dashboard.subtitle")}
          </Typography>
        </div>

        <Grid container spacing={3}>
          <Grid size={{ xs: 12, md: 4 }}>
            <StatCard
              title={t("dashboard.totalApps")}
              value={apps.length}
              icon={AppsRoundedIcon}
              color="#1976D2"
            />
          </Grid>

          <Grid size={{ xs: 12, md: 4 }}>
            <StatCard
              title={t("dashboard.available")}
              value="100%"
              icon={DashboardRoundedIcon}
              color="#43A047"
            />
          </Grid>

          <Grid size={{ xs: 12, md: 4 }}>
            <StatCard
              title={t("dashboard.tools")}
              value={apps.length}
              icon={BuildRoundedIcon}
              color="#FB8C00"
            />
          </Grid>
        </Grid>

        <SearchBar
          value={search}
          onChange={setSearch}
        />

        <Grid container spacing={3}>
          {apps.map((item) => (
            <Grid
              key={item.id}
              size={{
                xs: 12,
                sm: 6,
                md: 4,
                lg: 3,
              }}
            >
              <AppCard item={item} />
            </Grid>
          ))}
        </Grid>
      </Stack>
    </MainLayout>
  );
}