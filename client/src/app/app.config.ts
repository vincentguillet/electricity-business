import {
  ApplicationConfig,
  importProvidersFrom, inject, provideAppInitializer,
  provideZoneChangeDetection
} from '@angular/core';
import {provideRouter} from '@angular/router';

import {routes} from './app.routes';
import {BrowserModule} from '@angular/platform-browser';
import {provideHttpClient, withInterceptors} from '@angular/common/http';
import {authTokenInterceptor} from './interceptors/auth-token.interceptor';
import {AuthService} from './services/auth/auth.service';
import {catchError, firstValueFrom, of} from 'rxjs';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({eventCoalescing: true}),
    importProvidersFrom(BrowserModule),
    provideRouter(routes),
    provideHttpClient(withInterceptors([authTokenInterceptor])),
    provideAppInitializer(() => {
      const auth = inject(AuthService);
      return firstValueFrom(
        auth.bootstrapSession().pipe(
          catchError(() => of(null))
        )
      );
    })
  ]
};
