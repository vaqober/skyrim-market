import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UserRole } from '../../../models/user-role';

export function createLoginForm() {
  return new FormGroup({
    username: new FormControl(null, [Validators.required]),
    password: new FormControl(null, [Validators.required])
  })
}

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
