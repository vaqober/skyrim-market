import { UserRole } from '@models/user-role';
import { User } from '@models/user';

export function getUrlByUserRole(role: UserRole): string {
  switch (role) {
    case UserRole.CLIENT:
      return 'client'
    case UserRole.EMPLOYEE:
      return 'employee'
    case UserRole.MASTER:
      return 'master'
    default:
      throw new Error('Unknown Role')
  }
}

export function createFormData(user: User): FormData {
  const {username, password} = user
  const formData = new FormData()
  formData.append("username", username)
  formData.append("password", password)

  return formData;
}
