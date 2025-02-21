"use client";

import { useRouter } from "next/navigation";
import {
  Typography,
  Button,
  Box,
  Grid,
  Card,
  CardContent,
} from "@mui/material";
import Navbar from "@/app/widgets/navbar";

export default function Home() {
  const router = useRouter();

  return (
      <div>

        <Navbar />
        <Box sx={{ padding: 10 }}>
          <Typography variant="h4" align="center" gutterBottom marginY={10}>
            Efficiently manage employees and tasks in your organization with ease.
            Assign tasks, track progress, and organize your workforce seamlessly.
          </Typography>

          <Grid container spacing={4} justifyContent="center">

            <Grid item xs={12} sm={6} md={4} >
              <Card sx={{ textAlign: "center" }}>
                <CardContent>
                  <Typography variant="h5">Manage Tasks</Typography>
                  <Typography variant="body2" color="text.secondary" sx={{ marginBottom: 2 }}>
                    View, add, and manage tasks assigned to employees.
                  </Typography>
                  <Button variant="contained" color="primary" onClick={() => router.push("/pages/tasks")}>
                    View Tasks
                  </Button>
                </CardContent>
              </Card>
            </Grid>


            <Grid item xs={12} sm={6} md={4}>
              <Card sx={{ textAlign: "center" }}>
                <CardContent>
                  <Typography variant="h5">Manage Employees</Typography>
                  <Typography variant="body2" color="text.secondary" sx={{ marginBottom: 2 }}>
                    View, add, and manage employees in the organization.
                  </Typography>
                  <Button variant="contained" color="secondary" onClick={() => router.push("/pages/employees")}>
                    View Employees
                  </Button>
                </CardContent>
              </Card>
            </Grid>
          </Grid>
        </Box>
      </div>
  );
}
