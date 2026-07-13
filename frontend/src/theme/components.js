const components = {
  MuiButton: {
    defaultProps: {
      disableElevation: true,
    },

    styleOverrides: {
      root: {
        borderRadius: 12,
        height: 50,
        fontWeight: 600,
        textTransform: "none",
      },
    },
  },

  MuiCard: {
    styleOverrides: {
      root: {
        borderRadius: 20,
        boxShadow: "0 10px 30px rgba(15,76,129,.12)",
      },
    },
  },

  MuiTextField: {
    defaultProps: {
      fullWidth: true,
      variant: "outlined",
      margin: "normal",
    },
  },

  MuiPaper: {
    styleOverrides: {
      root: {
        borderRadius: 20,
      },
    },
  },

  MuiOutlinedInput: {
    styleOverrides: {
      root: {
        borderRadius: 12,

        "&:hover .MuiOutlinedInput-notchedOutline": {
          borderColor: "#0F4C81",
        },

        "&.Mui-focused .MuiOutlinedInput-notchedOutline": {
          borderWidth: 2,
        },
      },
    },
  },

  MuiAlert: {
    styleOverrides: {
      root: {
        borderRadius: 12,
      },
    },
  },
};

export default components;