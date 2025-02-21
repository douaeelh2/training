import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import axios from "axios";

// Hook for fetching the list of employees
export const useEmployeesList = () => {
    return useQuery({
        queryKey: ["employees"],
        queryFn: async () => {
            const response = await axios.get("http://localhost:7552/demo/employees/with-department");
            return response.data;
        },
    });
};

// Hook for adding a new employee
export const useAddEmployee = () => {
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: (data) => axios.post("http://localhost:7552/demo/employees", data).then((res) => res.data),
        onSuccess: () => {
            queryClient.invalidateQueries(["employees"]);
        },
    });
};
