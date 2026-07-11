const components = {
  MuiButton: {
    styleOverrides: {
      root: {
        borderRadius: 10,
        textTransform: "none",
        fontWeight: 600,
        height: 44,
      },
    },
  },

  MuiCard: {
    styleOverrides: {
      root: {
        borderRadius: 16,
        boxShadow: "0 8px 24px rgba(0,0,0,.08)",
      },
    },
  },

  MuiTextField: {
    defaultProps: {
      fullWidth: true,
      variant: "outlined",
    },
  },

  MuiPaper: {
    styleOverrides: {
      root: {
        borderRadius: 16,
      },
    },
  },
};

export default components;