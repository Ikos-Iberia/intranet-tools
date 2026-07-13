import {
  Card,
  CardActionArea,
  CardContent,
  Box,
  Typography,
} from "@mui/material";

import ArrowForwardRoundedIcon from "@mui/icons-material/ArrowForwardRounded";
import { useNavigate } from "react-router-dom";
import { useTranslation } from "react-i18next";

export default function AppCard({ item }) {
  const navigate = useNavigate();
  const { t } = useTranslation();

  const Icon = item.icon;

  return (
    <Card
      sx={{
        height: "100%",
        transition: ".25s",

        "&:hover": {
          transform: "translateY(-5px)",
          boxShadow: 8,
        },
      }}
    >
      <CardActionArea
        sx={{ height: "100%" }}
        onClick={() => navigate(item.path)}
      >
        <CardContent
          sx={{
            display: "flex",
            flexDirection: "column",
            height: "100%",
            gap: 2,
          }}
        >
          <Box
            sx={{
              width: 62,
              height: 62,
              borderRadius: 3,
              bgcolor: item.color,
              color: "white",
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
            }}
          >
            <Icon sx={{ fontSize: 34 }} />
          </Box>

          <Typography variant="h6" fontWeight={700}>
            {t(item.title)}
          </Typography>

          <Typography
            color="text.secondary"
            sx={{ flexGrow: 1 }}
          >
            {t(item.description)}
          </Typography>

          <Box
            sx={{
              display: "flex",
              justifyContent: "flex-end",
              alignItems: "center",
              color: "primary.main",
              gap: .5,
              fontWeight: 600,
            }}
          >
            {t("common.open")}

            <ArrowForwardRoundedIcon fontSize="small" />
          </Box>
        </CardContent>
      </CardActionArea>
    </Card>
  );
}