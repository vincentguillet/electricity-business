import {HttpErrorResponse, HttpInterceptorFn} from '@angular/common/http';
import {inject} from '@angular/core';
import {AuthService} from '../services/auth/auth.service';
import {catchError, switchMap, throwError} from 'rxjs';

let isRefreshing = false;

export const authTokenInterceptor: HttpInterceptorFn = (req, next) => {

  const authService: AuthService = inject(AuthService);
  const token = authService.getAccessToken();

  const authReq = token
    ? req.clone({setHeaders: {Authorization: `Bearer ${token}`}, withCredentials: true})
    : req.clone({withCredentials: true});

  return next(authReq).pipe(
    catchError((error: any) => {
      const is401 = error instanceof HttpErrorResponse && error.status === 401;

      if (is401 && !isRefreshing) {
        isRefreshing = true;
        return inject(AuthService).refresh().pipe(
          switchMap(newToken => {
            isRefreshing = false;
            if (!newToken) return throwError(() => error);
            const retryReq = req.clone({
              setHeaders: {Authorization: `Bearer ${newToken}`},
              withCredentials: true
            });
            return next(retryReq);
          }),
          catchError(err => {
            isRefreshing = false;
            return throwError(() => err);
          })
        );
      }

      return throwError(() => error);
    })
  );
};
