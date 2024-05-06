import {Category, Country, Language, UserRole} from "./Enums";

interface User {
    id?: number,
    username: string,
    password: string,
    roles: UserRole,
    language: Language,
    country: Country,
    category: Category,
    timeframe: string
}

export default User;
