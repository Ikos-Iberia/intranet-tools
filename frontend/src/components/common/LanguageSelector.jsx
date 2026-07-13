import { useTranslation } from "react-i18next";
import {
  FormControl,
  MenuItem,
  Select,
} from "@mui/material";

export default function LanguageSelector() {
  const { i18n } = useTranslation();

  const handleChange = (event) => {
    i18n.changeLanguage(event.target.value);
  };

  return (
    <FormControl size="small">
      <Select
        value={i18n.language}
        onChange={handleChange}
      >
        <MenuItem value="en">🇬🇧 English</MenuItem>
        <MenuItem value="es">🇪🇸 Español</MenuItem>
        <MenuItem value="el">🇬🇷 Ελληνικά</MenuItem>
      </Select>
    </FormControl>
  );
}