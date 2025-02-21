"use client";

import React from "react";
import { useEmployeesList } from "@/api/axios/employees";
import { styled } from "@mui/material/styles";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import CircularProgress from "@mui/material/CircularProgress";
import Alert from "@mui/material/Alert";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Navbar from "@/app/widgets/navbar";


const StyledTableCell = styled(TableCell)(({ theme }) => ({
    [`&.${tableCellClasses.head}`]: {
        backgroundColor: theme.palette.primary.main,
        color: theme.palette.common.white,
    },
    [`&.${tableCellClasses.body}`]: {
        fontSize: 14,
    },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
    "&:nth-of-type(odd)": {
        backgroundColor: theme.palette.action.hover,
    },
    "&:last-child td, &:last-child th": {
        border: 0,
    },
}));

export default function EmployeeList() {
    // Récupération des données des employés
    const { data: employees, isLoading, isError } = useEmployeesList();

    // Gestion de l'état de chargement et d'erreur
    if (isLoading) return <CircularProgress />;
    if (isError) return <Alert severity="error">Failed to load employees!</Alert>;

    // Gestion des actions de boutons (exemples)
    const handleEdit = (id) => {
        console.log(`Edit employee with id: ${id}`);
    };

    const handleDelete = (id) => {
        console.log(`Delete employee with id: ${id}`);
    };

    return (
        <div>
            <Navbar />

            <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', my: 5, px: 3 }}>
                <Typography variant="h4" component="h2">Employee List</Typography>
                <Box>
                    <Button variant="contained" color="primary" sx={{ mr: 1 }}>
                        Add Employee
                    </Button>

                </Box>
            </Box>
            <TableContainer component={Paper}>
                <Table sx={{ minWidth: 700 }} aria-label="employee table">
                    <TableHead>
                        <TableRow>
                            <StyledTableCell>ID</StyledTableCell>
                            <StyledTableCell>Name</StyledTableCell>
                            <StyledTableCell align="right">Email</StyledTableCell>
                            <StyledTableCell align="right">Position</StyledTableCell>
                            <StyledTableCell align="right">Department</StyledTableCell>
                            <StyledTableCell align="right"></StyledTableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {employees.map((employee) => (
                            <StyledTableRow key={employee.id}>
                                <StyledTableCell component="th" scope="row">
                                    {employee.id}
                                </StyledTableCell>
                                <StyledTableCell>{employee.name}</StyledTableCell>
                                <StyledTableCell align="right">{employee.email}</StyledTableCell>
                                <StyledTableCell align="right">{employee.role}</StyledTableCell>
                                <StyledTableCell align="right">{employee.department.depName}</StyledTableCell>
                                <StyledTableCell align="right">
                                    <Button
                                        variant="contained"
                                        color="secondary"
                                        onClick={() => handleEdit(employee.id)}
                                        sx={{ mr: 1 }}
                                    >
                                        Edit
                                    </Button>
                                    <Button
                                        variant="contained"
                                        color="error"
                                        onClick={() => handleDelete(employee.id)}
                                    >
                                        Delete
                                    </Button>
                                </StyledTableCell>
                            </StyledTableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
}