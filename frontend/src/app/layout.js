"use client";

import "./globals.css";
import { ThemeProvider } from "@mui/material";
import { theme } from "@/config/theme";
import { useState } from "react";
import { QueryClientProvider } from "@tanstack/react-query";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";

import queryClient from "@/config/ReactQuery/QueryClient";

export default function RootLayout({ children }) {
  const [mode, setMode] = useState("light");

  return (
      <html lang="en">
      <body>
      <ThemeProvider theme={theme(mode)}>
        <QueryClientProvider client={queryClient}>
          {children}
          <ReactQueryDevtools initialIsOpen={false} />
        </QueryClientProvider>
      </ThemeProvider>
      </body>
      </html>
  );
}
