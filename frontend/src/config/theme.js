import { createTheme } from "@mui/material/styles";

export const theme = (mode) => createTheme({
    palette: {
        mode: mode,
        primary: {
            main: "#5d63cc"
        },
        secondary: {
            main: mode === "dark" ? "#5d63cc" : "#303167",
            light: "#7BAFFF",
        },
        background : {
            default: mode === "dark" ? "#252525" : "F8F8F8"
        }
    }
})