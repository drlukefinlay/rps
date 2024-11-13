import { Routes } from '@angular/router';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { PlayPageComponent } from './components/play/play-page/play-page.component';
import { MetricsPageComponent } from './components/metrics/metrics-page/metrics-page.component';

export const routes: Routes = [
    { path: 'metrics', component: MetricsPageComponent },
    { path: '', component: PlayPageComponent },
    { path: '**', component: PageNotFoundComponent }
];
