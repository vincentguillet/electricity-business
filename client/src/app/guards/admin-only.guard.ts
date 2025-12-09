import {inject} from '@angular/core';
import {CanActivateFn, CanMatchFn, Router, UrlTree, ActivatedRouteSnapshot, Route} from '@angular/router';
import {AuthService} from '../services/auth/auth.service';

function requireAdmin(url?: string): boolean | UrlTree {
  const authService: AuthService = inject(AuthService);
  const router: Router = inject(Router);

  if (!authService.isLoggedIn()) {
    return router.createUrlTree(['/login'], {queryParams: {redirect: url ?? router.url}});
  }

  if (!authService.hasRole('Administrator')) {
    return router.parseUrl('/home');
  }

  return true;
}

export const adminOnlyCanActivate: CanActivateFn = (route: ActivatedRouteSnapshot): boolean | UrlTree =>
  requireAdmin(route?.url?.map(u => u.path).join('/') ?? '/admin');

export const adminOnlyCanMatch: CanMatchFn = (route: Route): boolean | UrlTree =>
  requireAdmin(route?.path ?? '/admin');
