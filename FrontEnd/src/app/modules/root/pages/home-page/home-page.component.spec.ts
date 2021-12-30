import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { HomePageComponent } from './home-page.component';
import { of } from 'rxjs';
 
import { HeroLoadingComponent } from '../../../../shared/components/hero-loading/hero-loading.component';
 import { LoadingPlaceholderComponent } from '../../../../shared/components/loading-placeholder/loading-placeholder.component';
import { MockComponent } from 'ng-mocks';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { By } from '@angular/platform-browser';
 

describe('HomePage', () => {
  let component: HomePageComponent;
  let fixture: ComponentFixture<HomePageComponent>;

  const heroServiceSpy = jasmine.createSpyObj('HeroService', ['searchHeroes']);

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [
        NoopAnimationsModule
      ],
      declarations: [
   
        MockComponent(HeroLoadingComponent),
        MockComponent(LoadingPlaceholderComponent),
        HomePageComponent
      ] 
    }).compileComponents();

    fixture = TestBed.createComponent(HomePageComponent);
    component = fixture.debugElement.componentInstance;
    
    fixture.detectChanges();
  }));

  it('should create component', (() => {
    expect(component).toBeTruthy();
  }));

  it('should initialice heroes', waitForAsync(() => {
    fixture.whenStable().then(() => {
      expect(fixture.debugElement.queryAll(By.css('app-hero-card')).length).toBe(1);
    });
  }));
});
