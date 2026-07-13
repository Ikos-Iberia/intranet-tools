import { Card, CardContent, Typography, Box } from "@mui/material";

export default function StatCard({
  title,
  value,
  icon: Icon,
  color = "primary.main",
}) {
  return (
    <Card sx={{ height: "100%" }}>
      <CardContent>
        <Box
          sx={{
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
          }}
        >
          <Box>
            <Typography
              variant="body2"
              color="text.secondary"
            >
              {title}
            </Typography>

            <Typography
              variant="h4"
              fontWeight={700}
            >
              {value}
            </Typography>
          </Box>

          {Icon && (
            <Box
              sx={{
                width: 58,
                height: 58,
                borderRadius: 3,
                bgcolor: color,
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                color: "white",
              }}
            >
              <Icon fontSize="large" />
            </Box>
          )}
        </Box>
      </CardContent>
    </Card>
  );
}