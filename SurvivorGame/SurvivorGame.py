import pygame
import random
import time
import math

pygame.init()

width, height = 1000, 600
window = pygame.display.set_mode((width, height))
pygame.display.set_caption("Merin's Game")

window.fill((120, 200, 80))
owidth, oheight = 50, 50  # Base size for the triangle

# Initialize font for score display and game over
pygame.font.init()
score_font = pygame.font.SysFont('Arial', 32)
game_over_font = pygame.font.SysFont('Arial', 64)

# Enemy colors and properties
ENEMY_TYPES = {
    'red': {'color': (220, 20, 60), 'health': 2, 'min_score': 0, 'points': 1},
    'blue': {'color': (30, 144, 255), 'health': 4, 'min_score': 6, 'points': 2},
    'gold': {'color': (255, 215, 0), 'health': 9, 'min_score': 17, 'points': 5}
}

def draw_triangle(surface, color, rect, direction):
    """Draw a triangle pointing in the specified direction"""
    if direction == "right":
        points = [
            (rect.left, rect.top),  # Top point
            (rect.left, rect.bottom),  # Bottom point
            (rect.right, rect.centery),  # Right point (pointing direction)
        ]
    elif direction == "left":
        points = [
            (rect.right, rect.top),  # Top point
            (rect.right, rect.bottom),  # Bottom point
            (rect.left, rect.centery),  # Left point (pointing direction)
        ]
    elif direction == "up":
        points = [
            (rect.left, rect.bottom),  # Bottom left
            (rect.right, rect.bottom),  # Bottom right
            (rect.centerx, rect.top),  # Top point (pointing direction)
        ]
    else:  # down
        points = [
            (rect.left, rect.top),  # Top left
            (rect.right, rect.top),  # Top right
            (rect.centerx, rect.bottom),  # Bottom point (pointing direction)
        ]
    pygame.draw.polygon(surface, color, points)
    return points

def draw_score(surface, score):
    """Draw the score in the top left corner"""
    score_text = score_font.render(f'Score: {score}', True, (0, 0, 0))
    surface.blit(score_text, (20, 20))

def draw_game_over(surface, final_score):
    """Draw the game over screen"""
    surface.fill((0, 0, 0))  # Black background
    game_over_text = game_over_font.render('GAME OVER', True, (255, 0, 0))
    score_text = score_font.render(f'Final Score: {final_score}', True, (255, 255, 255))
    restart_text = score_font.render('Press R to Restart or Q to Quit', True, (255, 255, 255))
    
    surface.blit(game_over_text, (width//2 - game_over_text.get_width()//2, height//2 - 100))
    surface.blit(score_text, (width//2 - score_text.get_width()//2, height//2))
    surface.blit(restart_text, (width//2 - restart_text.get_width()//2, height//2 + 100))

class Bullet:
    def __init__(self, x, y, direction):
        self.original_surface = pygame.Surface((30, 20), pygame.SRCALPHA)
        self.original_surface.fill((255, 0, 0))
        
        if direction is not None:
            self.direction = direction
        else:
            self.direction = pygame.K_UP
            
        self.rotated_surface = pygame.transform.rotate(self.original_surface, 90) if self.direction in [pygame.K_UP, pygame.K_DOWN] else self.original_surface
        self.rect = self.rotated_surface.get_rect(x=x, y=y)

    def move(self):
        movement = {"left": (-10, 0), "right": (10, 0), "up": (0, -10), "down": (0, 10), "z": (0, 0)}
        
        key_name = pygame.key.name(self.direction)
        self.rect.x += movement.get(key_name, (0, 0))[0]
        self.rect.y += movement.get(key_name, (0, 0))[1]

class Enemy:
    def __init__(self, x, y, enemy_type='red', spawn_from_edge=False):
        self.enemy_type = enemy_type
        self.properties = ENEMY_TYPES[enemy_type]
        self.health = self.properties['health']
        self.original_surface = pygame.Surface((owidth, oheight), pygame.SRCALPHA)
        pygame.draw.rect(self.original_surface, self.properties['color'], (0, 0, owidth, oheight))
        self.rect = self.original_surface.get_rect(x=x, y=y)
        
        if spawn_from_edge:
            self.rect.x, self.rect.y = (0, random.randint(0, height - oheight)) if random.choice([True, False]) else (width - owidth, random.randint(0, height - oheight))
        else:
            self.rect.x, self.rect.y = x, y
            
        self.speed = 0.6
        self.freeze_timer = 0.0

    def duplicate(self):
        return Enemy(self.rect.x, self.rect.y, self.enemy_type)

    def move_towards_obj1(self, obj1, clock):
        if self.freeze_timer <= 0:
            self.rect.x += self.speed if self.rect.x < obj1.x else -self.speed
            self.rect.y += self.speed if self.rect.y < obj1.y else -self.speed
        else:
            self.freeze_timer = max(0, self.freeze_timer - clock.get_time() / 1000.0)

    def draw(self, surface):
        pygame.draw.rect(surface, self.properties['color'], self.rect)
        # Draw health bar
        health_percentage = self.health / self.properties['health']
        health_width = self.rect.width * health_percentage
        health_height = 5
        health_y = self.rect.y - 10
        pygame.draw.rect(surface, (255, 0, 0), (self.rect.x, health_y, self.rect.width, health_height))  # Background
        pygame.draw.rect(surface, (0, 255, 0), (self.rect.x, health_y, health_width, health_height))  # Health

def get_bullet_spawn_positions(triangle_points, direction, score):
    """Calculate bullet spawn positions based on triangle points and score"""
    if score < 10:
        return [triangle_points[2]]  # Just the pointing direction
    elif score < 30:
        return [triangle_points[0], triangle_points[2]]  # Two points
    else:
        return triangle_points  # All three points

def spawn_enemy(score):
    """Spawn an enemy based on the current score"""
    available_types = [enemy_type for enemy_type, props in ENEMY_TYPES.items() 
                      if score >= props['min_score']]
    if available_types:
        enemy_type = random.choice(available_types)
        return Enemy(0, 0, enemy_type=enemy_type, spawn_from_edge=True)
    return None

import pygame
import random
import time
import math

# [Previous imports and initialization code remains the same until the main function]

def get_spawn_parameters(score):
    """Get enemy spawn parameters based on score"""
    if score < 10:
        return {'cooldown': 2.0, 'max_enemies': 2}
    elif score < 30:
        return {'cooldown': 1.5, 'max_enemies': 3}
    elif score < 55:
        return {'cooldown': 1.0, 'max_enemies': 4}
    else:
        return {'cooldown': 0.5, 'max_enemies': 5}  # Much faster spawn rate after score 55

def main():
    def reset_game():
        return {
            'obj1': pygame.Rect(500, 300, owidth, oheight),
            'enemies': [],
            'bullets': [],
            'score': 0,
            'last_bullet_time': 0,
            'last_enemy_spawn_time': time.time(),
            'last_key_pressed': pygame.K_RIGHT,
            'game_over': False
        }
    
    game_state = reset_game()
    
    bullet_cooldown = 0.25
    PLAYER_COLOR = (83, 104, 120, 200)
    clock = pygame.time.Clock()
    run = True
    
    while run:
        if not game_state['game_over']:
            window.fill((120, 200, 80))
            obj1_rect = pygame.Rect(game_state['obj1'].x, game_state['obj1'].y, owidth, oheight)
            
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    run = False
                    break

            keys = pygame.key.get_pressed()
            current_time = time.time()
            
            # Handle movement
            horizontal_movement = 0
            vertical_movement = 0
            
            for dir in ["left", "right", "up", "down"]:
                if keys[getattr(pygame, f"K_{dir.upper()}")]:
                    game_state['last_key_pressed'] = getattr(pygame, f"K_{dir.upper()}")
                    horizontal_movement += -2 if dir == "left" else 2 if dir == "right" else 0
                    vertical_movement += -2 if dir == "up" else 2 if dir == "down" else 0
            
            game_state['obj1'].x = max(0, min(width - owidth, game_state['obj1'].x + horizontal_movement))
            game_state['obj1'].y = max(0, min(height - oheight, game_state['obj1'].y + vertical_movement))
            
            # Draw and get triangle points
            direction = pygame.key.name(game_state['last_key_pressed'])
            triangle_points = draw_triangle(window, PLAYER_COLOR, obj1_rect, direction)
            
            # Handle shooting
            if keys[pygame.K_z] and current_time - game_state['last_bullet_time'] > bullet_cooldown:
                spawn_positions = get_bullet_spawn_positions(triangle_points, game_state['last_key_pressed'], game_state['score'])
                for pos in spawn_positions:
                    new_bullet = Bullet(pos[0] - 15, pos[1] - 10, game_state['last_key_pressed'])
                    game_state['bullets'].append(new_bullet)
                game_state['last_bullet_time'] = current_time

            # Update and draw bullets
            for bullet in game_state['bullets']:
                window.blit(bullet.rotated_surface, bullet.rect)
                bullet.move()
            
            # Handle collisions and enemy spawning
            bullets_to_remove = []
            enemies_to_remove = []
            
            # Get spawn parameters based on score
            spawn_params = get_spawn_parameters(game_state['score'])
            
            if current_time - game_state['last_enemy_spawn_time'] > spawn_params['cooldown'] and len(game_state['enemies']) < spawn_params['max_enemies']:
                new_enemy = spawn_enemy(game_state['score'])
                if new_enemy:
                    game_state['enemies'].append(new_enemy)
                    game_state['last_enemy_spawn_time'] = current_time
            
            # Check for bullet hits
            for bullet in game_state['bullets']:
                for enemy in game_state['enemies']:
                    if bullet.rect.colliderect(enemy.rect):
                        bullets_to_remove.append(bullet)
                        enemy.health -= 1
                        if enemy.health <= 0:
                            enemies_to_remove.append(enemy)
                            game_state['score'] += enemy.properties['points']
            
            # Check for player collision with enemies
            for enemy in game_state['enemies']:
                if obj1_rect.colliderect(enemy.rect):
                    game_state['game_over'] = True
            
            # Update enemy and bullet lists
            game_state['enemies'] = [enemy for enemy in game_state['enemies'] if enemy not in enemies_to_remove]
            game_state['bullets'] = [b for b in game_state['bullets'] if b not in bullets_to_remove]
            
            # Update and draw enemies
            for enemy in game_state['enemies']:
                enemy.move_towards_obj1(game_state['obj1'], clock)
                enemy.draw(window)
            
            # Draw score
            draw_score(window, game_state['score'])
            
        else:
            # Game Over Screen
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    run = False
                elif event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_r:
                        game_state = reset_game()
                    elif event.key == pygame.K_q:
                        run = False
            
            draw_game_over(window, game_state['score'])
        
        pygame.display.update()
        clock.tick(60)
        
    pygame.quit()

if __name__ == "__main__":
    main()
