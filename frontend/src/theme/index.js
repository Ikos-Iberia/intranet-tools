import { createTheme } from "@mui/material/styles";

import palette from "./palette";
import components from "./components";

const theme = createTheme({

  palette,

  typography: {
    fontFamily: "Inter, sans-serif",

    h4: {
      fontWeight: 700,
    },

    h5: {
      fontWeight: 700,
    },

    button: {
      fontWeight: 600,
    },
  },

  shape: {
    borderRadius: 12,
  },

  components,
});

export default theme;